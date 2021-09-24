package com.example.model

data class OperationModel(val a: Int = 0, val b: Int = 0, val c: Int = 0) {
    constructor(_c: Int) : this(c = _c)
    constructor(_a: Int, _b: Int) : this(a = _a, b = _b)
}