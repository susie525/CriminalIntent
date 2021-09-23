package com.example.criminalntent

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.Fragment

open class CrimeFragment: Fragment() {
    var mCrime: Crime = Crime()
    var mTitleField: EditText? = null
    var mDateButton: Button? = null
    var mSolvedCheckBox: CheckBox? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v: View = inflater.inflate(R.layout.fragment_crime, container, false)
        mTitleField = v.findViewById(R.id.crime_title)
        mTitleField?.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                //
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                mCrime.mTitle = s.toString()
            }
        })
        mDateButton = v.findViewById(R.id.crime_date)
        mDateButton?.text = mCrime.mDate.toString()
        mDateButton?.isEnabled = false

        mSolvedCheckBox = v.findViewById(R.id.crime_solved)
        mSolvedCheckBox?.setOnCheckedChangeListener { _, isChecked -> {
            mCrime.mSolved = isChecked
        } }
        return v
    }
}