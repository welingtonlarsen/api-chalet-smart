package br.com.chaletSmart

import io.micronaut.runtime.Micronaut.*
fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("br.com.chaletSmart")
		.start()
}

