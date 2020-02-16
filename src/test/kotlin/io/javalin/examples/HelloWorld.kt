/*
 * Javalin - https://javalin.io
 * Copyright 2017 David Åse
 * Licensed under Apache 2.0: https://github.com/tipsy/javalin/blob/master/LICENSE
 */

package io.javalin.examples

import io.javalin.Javalin

fun main(args: Array<String>) {
    val app = Javalin.create().port(7070).start()
    app.get("/") { ctx -> ctx.result("Hello World") }
}
