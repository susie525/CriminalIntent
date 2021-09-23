package com.example.criminalntent

import android.content.Context
import java.util.*

open class CrimeLab {
   var mCrimes: ArrayList<Crime> = arrayListOf()

   private constructor(){
      for(i in 0 until 99) {
         val crime: Crime = Crime()
         crime.mTitle = "Crime #$i"
         crime.mSolved = i % 2 == 0
         mCrimes.add(crime)
      }
   }

   fun getCrime(id: UUID): Crime? {
      for(crime in mCrimes) {
         if (crime.mId == id) {
            return crime
         }
      }
      return null
   }

   companion object {
      var sCrimeLab: CrimeLab? = null
      fun get(context: Context): CrimeLab {
         if(sCrimeLab == null) {
            sCrimeLab = CrimeLab()
         }
         return sCrimeLab!!
      }
   }
}