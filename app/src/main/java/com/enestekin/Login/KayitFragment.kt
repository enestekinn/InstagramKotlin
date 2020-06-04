package com.enestekin.Login

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.enestekin.Models.Users

import com.enestekin.instagramkotlin.R
import com.enestekin.utils.EventbusDataEvents
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_kayit.*
import kotlinx.android.synthetic.main.fragment_kayit.view.*
import kotlinx.android.synthetic.main.fragment_phone.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class KayitFragment : Fragment() {

    var telno=""
    var verificationId = ""
    var gelenKod= ""
    var gelenEmail = ""
    lateinit var mAuth :FirebaseAuth
    lateinit var mRef : DatabaseReference   //veri tabani
    var emailKayitIslemi=true
    lateinit var  progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Sistemde kullanici varsa onu sistemden cikarma kodu
/*if (mAuth.currentUser !=null) {
    mAuth.signOut()
}*/

        // Inflate the layout for this fragment
     var   view =  inflater.inflate(R.layout.fragment_kayit, container, false)

        mAuth = FirebaseAuth.getInstance()
        mRef = FirebaseDatabase.getInstance().reference
        view.editTextName.addTextChangedListener(watcher)
        view.editTextPassword.addTextChangedListener(watcher)
       view.editTextUsername.addTextChangedListener(watcher)
        progressBar=view.progressbarRegister

        view.button3.setOnClickListener {
//kullanici e mail ile kayit olmak istiyor
            if (emailKayitIslemi) {

                progressBar.visibility=View.VISIBLE

                var sifre = view.editTextPassword.text.toString()
                var name_and_surname = view.editTextName.text.toString()
                var userName = view.editTextUsername.text.toString()

                //UUid asagidaki gibi aliniyor  ikisinden birisini kullanabiliriz
                var userid = mAuth.currentUser!!.uid.toString()
               // var userid2 = UUID.randomUUID().toString()

                mAuth.createUserWithEmailAndPassword(gelenEmail,sifre)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {  // kullanici bilgilerle dogru giris yapmismi onu kontrol ettik

                            progressBar.visibility=View.INVISIBLE


                            Toast.makeText(context,"Successfuly Sign In ",Toast.LENGTH_SHORT).show()
                            //oturum acan kullanicinin bilgilerini database e kaydediyoruz
                            var kaydedilenKullanici= Users(gelenEmail,sifre,userName,name_and_surname,"","",userid)

                            //Burada firebasede gorecegimiz kullanici  veritabini olusturduk
                            mRef.child("Users").child(userid).setValue(kaydedilenKullanici)
                                .addOnCompleteListener {
                                    if(it.isSuccessful){
                                        progressBar.visibility=View.INVISIBLE

                                        Toast.makeText(context,"User Created ",Toast.LENGTH_SHORT).show()
                                        view.progressbarRegister.visibility=View.GONE

                                    }else {
                                        progressBar.visibility=View.INVISIBLE


                                        //kullanici hesap olustururken hataolursa...!!!!
                                        mAuth.currentUser!!.delete()
                                            .addOnCompleteListener {
                                                if(it.isSuccessful){
                                                    Toast.makeText(context,"User not Created ",Toast.LENGTH_SHORT).show()

                                                }

                                    }
                                }}

                        }else {
                            Toast.makeText(context,"Couldn't Sign In "+it.exception,Toast.LENGTH_SHORT).show()

                        }
                    }
                //kullanici telefon ile kayit olmak istiyor  emailKayitIslemi bu durumda falsedur.
            } else {
                var sifre =view.editTextPassword.text.toString()

                //Burasi cok onemli kullanici integer formatinda girdigi telefon numarasini emaile cevirip
                //kullaniciyi email seklinde kayit ediyoruz
                var sahteEmail = telno+"@enes.com"

                var userName = view.editTextUsername.text.toString()
                var name_and_surname = view.editTextName.text.toString()


                //UUid asagidaki gibi aliniyor  ikisinden birisini kullanabiliriz
                var userid = mAuth.currentUser!!.uid.toString()
                // var userid2 = UUID.randomUUID().toString()

                mAuth.signInWithEmailAndPassword(sahteEmail,sifre)
                    .addOnCompleteListener {
                        if(it.isSuccessful) {

                            progressBar.visibility=View.INVISIBLE

                            Toast.makeText(context,"Successfuly Sign In with Phone Number "+mAuth.currentUser
                                !!.uid,Toast.LENGTH_SHORT).show()
                            //oturum acan kullanicinin bilgilerini database e kaydediyoruz
                            var kaydedilenKullanici= Users("",sifre,userName,name_and_surname, telno,sahteEmail,userid)

                            //Burada firebasede gorecegimiz kullanici  veritabini olusturduk
                            mRef.child("Users").child(userid).setValue(kaydedilenKullanici)
                                .addOnCompleteListener {
                                    if(it.isSuccessful){
                                        view.progressbarRegister.visibility=View.GONE

                                        Toast.makeText(context,"User Created ",Toast.LENGTH_SHORT).show()

                                    }else {  // Kullanici kaydedilmemisse kullaniciyi siliyoruz aksi taktirde veriler tutarsiz olur
                                        progressBar.visibility=View.INVISIBLE

                                        mAuth.currentUser!!.delete()
                                            .addOnCompleteListener {
                                                if(it.isSuccessful){
                                                    Toast.makeText(context,"User not Created ",Toast.LENGTH_SHORT).show()

                                                }
                                            }

                                    }
                                }

                        }else{
                            progressBar.visibility=View.INVISIBLE

                            Toast.makeText(context,"Couldn't Sign In "+it.exception,Toast.LENGTH_SHORT).show()

                        }
                    }


            }


        }
        return view
    }
    var watcher : TextWatcher = object  : TextWatcher{  // yukarida olusturdugumuz watcheri boyle  tanimliyoruz
        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
           if (s!!.length>5){
               if (editTextName.text.toString().length>5 && editTextPassword.text.toString().length>5
                   && editTextUsername.text.toString().length>5){

                   button3.isEnabled=true
                  button3.setBackgroundResource(R.drawable.button_register_edit_color)
               }else {      // button aktifse  pasiflestirir
                   button3.isEnabled=false

                   button3.setBackgroundResource(R.color.white)
               }

           }else  {
               button3.isEnabled=false

               button3.setBackgroundResource(R.color.white)



           }
        }

    }



////////////////////////////////////////////////////////////////////////
    @Subscribe(sticky = true)
    internal fun onEmailEvents (kayitbilgileri: EventbusDataEvents.KayitBilgileriniGonder){
        if(kayitbilgileri.emailKayit==true ){ //email kayit dogru ise kullanici email ile gelmis register activitede bunu ele aldik.
            emailKayitIslemi=true
             gelenEmail = kayitbilgileri.email!!

            Toast.makeText(activity,"Gelen email : "+gelenEmail,Toast.LENGTH_LONG).show()

        }else {   // kullanici telefon ile gelmistir burada buna gore islem yaptik.
            emailKayitIslemi=false
            telno=kayitbilgileri.telNo!!
            verificationId = kayitbilgileri.verificationID!!
            gelenKod= kayitbilgileri.code!!

           Toast.makeText(activity,"Gelen kod : "+gelenKod+"VerificationId : "+ verificationId+"Tel no : "+telno,Toast.LENGTH_LONG).show()

        }

    }
    override fun onAttach(context: Context) {
        EventBus.getDefault().register(this)
        super.onAttach(context)
    }

    override fun onDetach() {
        EventBus.getDefault().unregister(this)
        super.onDetach()
    }

}
