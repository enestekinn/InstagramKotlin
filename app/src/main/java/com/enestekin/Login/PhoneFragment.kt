package com.enestekin.Login

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.enestekin.instagramkotlin.R
import com.enestekin.utils.EventbusDataEvents
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.oAuthCredential
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_phone.*
import kotlinx.android.synthetic.main.fragment_phone.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.util.concurrent.TimeUnit
import kotlinx.android.synthetic.main.fragment_phone.view.button60


class PhoneFragment : Fragment() {


    var gelenTelno = ""
   lateinit var callbacks : PhoneAuthProvider.OnVerificationStateChangedCallbacks
    var verificationID = ""
    var gelenKod =""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        var view = inflater.inflate(R.layout.fragment_phone, container, false)
view.textViewUserPhoneNumber.setText(gelenTelno)
setupCallback()

  view.button60.setOnClickListener {
        if (editText.text.toString().equals(gelenKod)) {
            EventBus.getDefault().postSticky(EventbusDataEvents.KayitBilgileriniGonder(editText.text.toString()
                , null, verificationID,gelenKod,null))
            val transaction =activity!!.supportFragmentManager.beginTransaction()
            transaction.replace(R.id.registerCointainer,KayitFragment())
            transaction.addToBackStack("Login")
            transaction.commit()

        }
            else{
            Toast.makeText(activity,"Kodlar hatali",Toast.LENGTH_LONG).show()

         /*   val transaction =activity!!.supportFragmentManager.beginTransaction()
            transaction.replace(R.id.registerCointainer,KayitFragment())
            transaction.addToBackStack("Login")
            transaction.commit() */

        }
        }

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            gelenTelno, // Phone number to verify
            60, // Timeout duration
            TimeUnit.SECONDS, // Unit of timeout
            this!!.activity!!, // Activity (for callback binding)
            callbacks) // OnVerificationStateChangedCallbacks
        return view
    }

    private fun setupCallback() {
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                // if bolguunda yazdik cunku kullanici giris yapti tekrar giris yaptiginda firebase kullaniciya sms gondermicek  daha once  gonderdigi icin
                gelenKod = credential.smsCode!!

              /*  if(credential.smsCode.isNullOrEmpty()){
                    gelenKod = credential.smsCode!!
                    Log.e("enes","Giris yapildi")
                    progressBarVerification.visibility=View.INVISIBLE



                }*/


            }

            override fun onVerificationFailed(e: FirebaseException) {
 Log.e("Hata : "," "+e.localizedMessage) //Firebasedeki hatayi bu sekilde buluyoruz
progressBarVerification.visibility=View.INVISIBLE
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
              verificationID = verificationId


                progressBarVerification.visibility= View.INVISIBLE

            }
        }


    }

    //anotation   bir yayina subscribe oluyoruz yani yayin varsa gelsin :(
    @Subscribe (sticky = true)
  internal  fun onTelefonEvents (kayitbilgileri: EventbusDataEvents.KayitBilgileriniGonder) {
     gelenTelno =kayitbilgileri.telNo!!


        Log.e("enes","gelen telefon numarasi "+ gelenTelno)

    }
// eventbus kutuphanesinin iki methodu  yayin acik yayin kapali
    override fun onAttach(context: Context) {
    EventBus.getDefault().register(this)

        super.onAttach(context)
    }

    override fun onDetach() {
        EventBus.getDefault().unregister(this)

        super.onDetach()
    }

}
