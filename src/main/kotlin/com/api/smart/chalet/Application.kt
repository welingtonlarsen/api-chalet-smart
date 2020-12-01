package com.api.smart.chalet

import io.micronaut.runtime.Micronaut.*
fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("com.api.smart.chalet")
		.start()
}

