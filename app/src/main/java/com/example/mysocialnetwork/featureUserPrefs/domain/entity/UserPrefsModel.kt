package com.example.mysocialnetwork.featureUserPrefs.domain.entity

data class UserPrefsModel(
    var id: String,
    val dbId: String,
    var name: String,
    val email: String,
    var gender: String,
    var age: Int,
    var userImg: String?
) {
    fun getImg(): String {
        if (userImg == null)
            return "https://firebasestorage.googleapis.com/v0/b/my-social-network-d4ed8.appspot.com/o/pngwing.com.png?alt=media&token=0c53ca91-0e1f-4e3b-ad16-b030b6c156ea"
        return userImg as String
    }
}
