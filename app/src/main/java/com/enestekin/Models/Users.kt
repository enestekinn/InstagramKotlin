package com.enestekin.Models

class Users {
    var email: String? = null
    var password: String? = null
    var username: String? = null
    var name_and_surname: String? = null
    var phoneNumber: String? = null
    var emailPhoneNumber: String? = null
    var userId : String? =null   // bunu her constructorda olacak cunku email ilede girse telefon iledede girse kullanicinin user  idsi olacak

    // 3 tane constructor olusturduk email ile giris yapinca  phone ile giris yapinca birtanede bos :)
    //Constructorlardan bir tanesini sildik cunku  kullanicinin e malini okudugumuzda  telefon ile giris yapanin e maili olmadigi icin nullpointer
    //exception hatasi alacaktik
    constructor() {}
    constructor(
        email: String?,
        password: String?,
        username: String?,
        name_and_surname: String?,
        phoneNumber: String?,
        emailPhoneNumber: String?,
        userId : String?
    ) {
        this.email=email
        this.password = password
        this.username = username
        this.name_and_surname = name_and_surname
        this.phoneNumber = phoneNumber
        this.emailPhoneNumber = emailPhoneNumber
        this.userId=userId
    }


}