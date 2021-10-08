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
import java.util.*

open class CrimeFragment: Fragment() {
    var mCrime: Crime = Crime()
    var mTitleField: EditText? = null
    var mDateButton: Button? = null
    var mSolvedCheckBox: CheckBox? = null

    companion object{
        private const val ARG_CRIME_ID = "crime_id"

        fun newInstance(crimeId: UUID): CrimeFragment{
            val args = Bundle()
            args.putSerializable(ARG_CRIME_ID, crimeId)

            val fragment = CrimeFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val crimeId = arguments?.getSerializable(ARG_CRIME_ID) as UUID
        mCrime = CrimeLab.get(activity!!).getCrime(crimeId)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v: View = inflater.inflate(R.layout.fragment_crime, container, false)
        mTitleField = v.findViewById(R.id.crime_title) as EditText
        mTitleField!!.text = Editable.Factory.getInstance().newEditable(mCrime.mTitle)
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

        mSolvedCheckBox = v.findViewById(R.id.crime_solved) as CheckBox
        mSolvedCheckBox!!.isChecked = mCrime.mSolved
        mSolvedCheckBox?.setOnCheckedChangeListener { _, isChecked ->
            run {
                mCrime.mSolved = isChecked
            }
        }
        return v
    }
}