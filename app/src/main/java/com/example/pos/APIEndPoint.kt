package com.example.pos

class APIEndPoint {
    companion object {
        private val SERVER = "https://KewanKu.000webhostapp.com/kewanku/"
        val READ = SERVER+"read.php"
        val delete = SERVER+"delete.php"
        val update = SERVER+"update.php"
    }
}