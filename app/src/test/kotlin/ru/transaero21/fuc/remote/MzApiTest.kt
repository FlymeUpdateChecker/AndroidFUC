package ru.transaero21.fuc.remote

import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.utils.io.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import ru.transaero21.fuc.entity.dto.info.common.error.ErrorInfo
import ru.transaero21.fuc.entity.dto.info.common.success.SuccessInfo
import ru.transaero21.fuc.utils.Utils
import ru.transaero21.fuc.utils.UtilsV1
import ru.transaero21.fuc.utils.UtilsV2
import kotlin.reflect.full.memberProperties

class MzApiTest {
    @Test
    fun api_checkGoodResponseForV1_isSuccess() {
        runBlocking {
            val engine = MockEngine {
                respond(
                    content = ByteReadChannel("""{"reply":{"code":200,"message":"","value":{"new":null,"plan":null,"cur":null}}}"""),
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }
            MzApi(engine).checkSysV1(UtilsV1.sys, DEFAULT_HOST).let { info ->
                assertNotNull(info)
                assert(info is SuccessInfo)
                assertEquals("200", (info as SuccessInfo).reply.code)
            }
        }
    }

    @Test
    fun api_checkErrorResponseForV1_isError() {
        runBlocking {
            val engine = MockEngine {
                respond(
                    content = ByteReadChannel("""{"code":"198002","message":"Signature error","redirect":"","value":""}"""),
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }
            MzApi(engine).checkSysV1(UtilsV1.sys, DEFAULT_HOST).let { info ->
                assertNotNull(info)
                assert(info is ErrorInfo)
                assertEquals("198002", (info as ErrorInfo).code)
            }
        }
    }

    @Test
    fun api_checkFatalResponseForV1_isNull() {
        runBlocking {
            val engine = MockEngine {
                respond(
                    content = ByteReadChannel("""empty"""),
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }
            assertNull(MzApi(engine).checkSysV1(UtilsV1.sys, DEFAULT_HOST))
        }
    }

    @Test
    fun utils_appendParamsInUrlBuild_isAdded() {
        listOf(UtilsV1.sys, UtilsV1.firmware, UtilsV2.sys, UtilsV2.firmware).forEach { sys ->
            runBlocking {
                MzApi(MockEngine { respond(content = ByteReadChannel("")) }).let { api ->
                    val appendParam = Utils.getAnyFunction(api, "appendParam")
                    assertNotNull(appendParam)
                    val client = Utils.getAnyMember(api, "client")
                    assertNotNull(client)
                    (client?.getter?.call(api) as HttpClient?)?.let { cl ->
                        cl.get("/test") {
                            url { url ->
                                appendParam?.call(api, url, sys)
                                sys::class.memberProperties.forEach { obj ->
                                    parameters[obj.name]?.let {
                                        assertEquals(it, obj.getter.call(sys).toString())
                                    } ?: assertNotNull(null)
                                }
                            }
                        }
                    } ?: assertNotNull(null)
                }
            }
        }
    }
}