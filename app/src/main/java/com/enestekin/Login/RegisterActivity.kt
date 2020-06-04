package com.enestekin.Login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.enestekin.instagramkotlin.R
import com.enestekin.utils.EventbusDataEvents
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.view.*
import kotlinx.android.synthetic.main.fragment_kayit.*
import org.greenrobot.eventbus.EventBus

class RegisterActivity : AppCompatActivity() ,FragmentManager.OnBackStackChangedListener {

    lateinit var manager: FragmentManager

    lateinit var mRef : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        //onbackstagedchanged de kullanacagimiz manageri olusturduk orada erisemiyoruz
        manager = supportFragmentManager
        manager.addOnBackStackChangedListener(this)
        init()


        //databaseden nesne olusturduk
        mRef =FirebaseDatabase.getInstance().reference


    }

    private fun init() {
        textviewEmail.setOnClickListener {
            view.visibility = View.VISIBLE
            viewtelefon.visibility = View.INVISIBLE
            editText.setText("")
            editText.hint = "E-MAIL"
            editText.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
            button2.setBackgroundResource(R.color.white)


        }
        texViewTelefon.setOnClickListener {
            view.visibility = View.INVISIBLE
            viewtelefon.visibility = View.VISIBLE
            editText.setText("")
            editText.hint = "PHONE"
            editText.inputType = InputType.TYPE_CLASS_PHONE
            button2.setBackgroundResource(R.color.white)




        }

        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            // count sayisi karakter sayisidir onemli!!! start ve beforelari koymayi unutma
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s!!.length >= 10) {

                    if (editText.text.toString().length >= 10) {
                        button2.isEnabled = true
                        button2.setBackgroundResource(R.drawable.button_register_edit_color)
                    } else {
                        button2.isEnabled = false
                        button2.setBackgroundResource(R.color.white)
                    }
                } else {
                    button2.setBackgroundResource(R.color.white)
                    button2.isEnabled = false

                }
            }
        })

        button2.setOnClickListener {
           if (isValidPhone(editText.text.toString())){
               if (editText.hint.toString().equals("PHONE NUMBER")) {   // kullanici telefona mi tikla emaile mi tikladi onu anliyoruz
                   registerRoot.visibility = View.GONE
                   val transaction = supportFragmentManager.beginTransaction()
                   transaction.replace(R.id.registerCointainer, PhoneFragment())
                   transaction.addToBackStack("phoneFragment")
                   transaction.commit()
                   //eventbus kutuphanesi neden STICKY alem fm yayin yapiyor radyo acik degil
                   EventBus.getDefault().postSticky(
                       EventbusDataEvents.KayitBilgileriniGonder(
                           editText.text.toString()
                           , null, null, null, false
                       )
                   )
               }else{
                   Toast.makeText(applicationContext,"Error",Toast.LENGTH_SHORT).show()
               }
           }
            else {
               if (isValidEmail(editText.text.toString())){

                  //Databaseden veri okuyoruz   Users kismi databasede ne yazdiysak birebir aynisini yazmaliyiz
                   mRef.child("Users").addListenerForSingleValueEvent(object  : ValueEventListener{
                       override fun onCancelled(p0: DatabaseError) {
                           TODO("Not yet implemented")
                       }

                       override fun onDataChange(p0: DataSnapshot) {
                           TODO("Not yet implemented")
                       }

                   }
                   )
                   registerRoot.visibility = View.GONE
                   val transaction = supportFragmentManager.beginTransaction()
                   transaction.replace(R.id.registerCointainer, KayitFragment())
                   transaction.addToBackStack("emailStacks")
                   transaction.commit()
                   //Veriyi eventbus a yollama
                   EventBus.getDefault().postSticky(
                       EventbusDataEvents.KayitBilgileriniGonder(
                           null, editText.text.toString(),
                           null, null, true
                       )
                   )

               }else {
                   Toast.makeText(applicationContext,"Error",Toast.LENGTH_SHORT).show()
               }

            }
        }

    }
    /*  override fun onBackPressed() {
        registerRoot.visibility=View.VISIBLE
        super.onBackPressed()
    }  sonradan bu fonksiyonu iptal ettik asagidaki gibi  toplam stack sayimzi 0 sa registerroot aktif oluyor*/

    override fun onBackStackChanged() {
        val stackcount =
            manager.backStackEntryCount  // stack kount sifir demek aktivitemizde fragment kalmadi demek
        if (stackcount == 0) {
            registerRoot.visibility = View.VISIBLE
        }

        // manager.popBackStack()   // sirayla actigimiz fragmentleri sirayla kapatir geri gelirsek
    }

    fun isValidEmail(givenEmail: String): Boolean {
        if (givenEmail == null) {
            return false
        }

         return   android.util.Patterns.EMAIL_ADDRESS.matcher(givenEmail).matches()  // kullanici string ifadeyi email formatinda girmismi onu kontrol ediyoruz

    }
    fun isValidPhone (givenPhone : String) : Boolean {
        if (givenPhone ==null || givenPhone.length>14) {

            return  false
        }
        return android.util.Patterns.PHONE.matcher(givenPhone).matches()   // kullanici string ifadeyi telefon formatinda girmismi onu kontrol ediyoruz
    }

}



