package com.example.billspliter.utilities

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LoggingController {

    private val logger = LoggerFactory.getLogger(LoggingController::class.java)

        @RequestMapping("/")
        fun index(): String {
            logger.trace("A TRACE Message");
            logger.debug("A DEBUG Message");
            logger.info("An INFO Message");
            logger.warn("A WARN Message");
            logger.error("An ERROR Message");

            return "Howdy! Check out the Logs to see the output...";
        }
}