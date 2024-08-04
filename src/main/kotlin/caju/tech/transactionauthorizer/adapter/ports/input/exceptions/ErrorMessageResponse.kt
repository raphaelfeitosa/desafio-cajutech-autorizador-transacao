package caju.tech.transactionauthorizer.adapter.ports.input.exceptions

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL
import java.time.LocalDateTime

@JsonInclude(NON_NULL)
class ErrorMessageResponse(
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val code: String?,
    val message: String?,
    val path: String,
    val errors: HashMap<String, String?>? = null,
)