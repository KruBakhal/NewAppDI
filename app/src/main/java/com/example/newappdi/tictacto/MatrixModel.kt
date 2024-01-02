package com.example.newappdi.tictacto

data class MatrixModel(val id: Int, var user: String?=null, var row: Int, var columns: Int) {
    override fun toString(): String {
        return "pos: ${row}${columns} user:$user"
    }
}