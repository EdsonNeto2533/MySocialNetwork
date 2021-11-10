package com.example.mysocialnetwork.featureDashboard.domain.entity

data class UserDashboardModel(
    var id: String,
    val name: String,
    val email: String,
    val gender: String,
    val age: Int,
    val userImg: String?
) {
    fun getImg(): String {
        if (userImg == null)
            return "https://firebasestorage.googleapis.com/v0/b/my-social-network-d4ed8.appspot.com/o/pngwing.com.png?alt=media&token=0c53ca91-0e1f-4e3b-ad16-b030b6c156ea"
        return userImg
    }
}
