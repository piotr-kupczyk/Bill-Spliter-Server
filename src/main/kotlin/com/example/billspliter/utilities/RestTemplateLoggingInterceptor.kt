import com.example.billspliter.groupmanagement.controller.GroupController
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpRequest
import org.springframework.http.HttpStatus
import org.springframework.http.client.*
import org.springframework.lang.Nullable
import org.springframework.util.StreamUtils
import java.io.*
import java.io.ByteArrayInputStream
import java.io.IOException

class RestTemplateLoggingInterceptor : ClientHttpRequestInterceptor {
    private val log = LoggerFactory.getLogger(GroupController::class.java)

    @Throws(IOException::class)
    override fun intercept(request: HttpRequest, body: ByteArray, execution: ClientHttpRequestExecution): ClientHttpResponse {
        // Show request object
        log.info("RestTemplate Request: URI={}, Headers={}, Body={}",
                request.uri,
                request.headers,
                String(body))

        // Fetch response
        val response = BufferingClientHttpResponseWrapper(execution.execute(request, body))

        // load response object
        val inputStringBuilder = StringBuilder()
        val bufferedReader = BufferedReader(InputStreamReader(response.body, "UTF-8"))
        var line: String? = bufferedReader.readLine()
        while (line != null) {
            inputStringBuilder.append(line)
            inputStringBuilder.append('\n')
            line = bufferedReader.readLine()
        }

        // Show response object
        log.info("RestTemplate Response: StatusCode={} {}, Headers={}, Body={}",
                response.statusCode,
                response.statusText,
                response.headers,
                inputStringBuilder.toString()
        )

        return response
    }

    private class BufferingClientHttpResponseWrapper internal constructor(private val response: ClientHttpResponse) : ClientHttpResponse {
        override fun getHeaders(): HttpHeaders {
            return this.response.headers
        }

        override fun getStatusCode(): HttpStatus {
            return this.response.statusCode
        }

        override fun getRawStatusCode(): Int {
            return this.response.rawStatusCode
        }

        override fun close() {
            this.response.close()
        }

        override fun getStatusText(): String {
            return this.response.statusText
        }

        override fun getBody(): InputStream {
            if (this.body == null) {
                this.body = StreamUtils.copyToByteArray(this.response.body)
            }

            return ByteArrayInputStream(this.body)
        }

        @Nullable
        private var body: ByteArray? = null
    }
}
