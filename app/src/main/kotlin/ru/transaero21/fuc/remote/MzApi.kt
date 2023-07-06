package ru.transaero21.fuc.remote

import android.util.Log
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.util.InternalAPI
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import ru.transaero21.fuc.entity.dto.info.common.MzInfo
import ru.transaero21.fuc.entity.dto.info.common.error.ErrorInfo
import ru.transaero21.fuc.entity.dto.info.common.success.SuccessInfo
import ru.transaero21.fuc.entity.dto.input.v1.SysV1Input
import ru.transaero21.fuc.entity.dto.input.v2.SysV2Input
import javax.inject.Inject
import kotlin.reflect.full.memberProperties

private const val TAG = "MzApi"

class MzApi @Inject constructor(
    engine: HttpClientEngine
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
        install(Logging) {
            logger = object: Logger {
                override fun log(message: String) {
                    Log.d(TAG, message)
                }
            }
            level = LogLevel.INFO
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
                this.host = host
                url { appendParam(sys) }
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