package ru.transaero21.fuc.remote

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import ru.transaero21.fuc.entity.dto.info.common.MzInfo
import ru.transaero21.fuc.entity.dto.info.common.error.ErrorInfo
import ru.transaero21.fuc.entity.dto.info.common.success.SuccessInfo
import ru.transaero21.fuc.entity.dto.input.v1.SysV1Input
import ru.transaero21.fuc.entity.dto.input.v2.SysV2Input
import kotlin.reflect.full.memberProperties

@Module
@InstallIn(SingletonComponent::class)
class MzApi(
    engine: HttpClientEngine = OkHttp.create()
) : IMzApi {
    @OptIn(ExperimentalSerializationApi::class)
    private val client = HttpClient(engine) {
        install(ContentNegotiation) {
            json(Json {
                explicitNulls = true
                ignoreUnknownKeys = true
                isLenient = true
            })
        }
    }

    override suspend fun checkSysV1(sys: SysV1Input, host: String): MzInfo? {
        return try {
            client.get("sysupgrade/check") {
                this.host = host
                url { appendParam(sys) }
            }.toMzInfo()
        } catch (ex: Exception) {
            null
        }
    }

    override suspend fun checkSysV2(sys: SysV2Input, host: String): MzInfo? {
        return try {
            client.get("sysupgrade/v2.0/check") {
                url {
                    this.host = host
                    appendParam(sys)
                }
            }.toMzInfo()
        } catch (ex: Exception) {
            null
        }
    }

    private suspend fun HttpResponse.toMzInfo(): MzInfo {
        return try {
            body<SuccessInfo>()
        } catch (ex: Exception) {
            body<ErrorInfo>()
        }
    }

    private fun URLBuilder.appendParam(obj: Any) {
        obj::class.memberProperties.let {
            it.forEach { m ->
                parameters.append(m.name, m.getter.call(obj).toString())
            }
        }
    }
}