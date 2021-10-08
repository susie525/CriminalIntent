package com.example.criminalntent

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CrimeListFragment: Fragment() {
    var mCrimeRecyclerView: RecyclerView? = null
    private var mAdapter: CrimeAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_crime_list, container, false)
        mCrimeRecyclerView = view.findViewById(R.id.crime_recycler_view) as RecyclerView
        mCrimeRecyclerView!!.layoutManager = LinearLayoutManager(activity)

        updateUI()
        return view
    }

    override fun onResume() {
        super.onResume()
        updateUI()
    }

    private fun updateUI() {
        val mCrimeLab: CrimeLab? = activity?.let { CrimeLab.get(it) }
        if (mAdapter == null) {
            mAdapter = mCrimeLab?.mCrimes?.let { CrimeAdapter(it) }
            mCrimeRecyclerView?.adapter = mAdapter
        } else {
            mAdapter!!.notifyDataSetChanged()
        }

    }

    private inner class CrimeHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.list_item_crime, parent, false)), View.OnClickListener {
        var mCrime: Crime? = null
        private val mTitleTextView: TextView = itemView.findViewById(R.id.crime_title)
        private val mDateTextView: TextView = itemView.findViewById(R.id.crime_date)
        private val mImageView: ImageView = itemView.findViewById(R.id.crime_solved)

        init {
            itemView.setOnClickListener(this)
        }
        fun bind(crime: Crime) {
            mCrime = crime
            mTitleTextView.text = mCrime!!.mTitle
            mDateTextView.text = mCrime!!.mDate.toString()
            mImageView.visibility = if (mCrime!!.mSolved) View.VISIBLE else View.INVISIBLE
        }

        override fun onClick(v: View?) {
            val intent = mCrime?.mId?.let { activity?.let { it1 -> CrimeActivity.newIntent(it1, it) } }
            startActivity(intent)
        }
    }

    private inner class CrimeAdapter(crimes: List<Crime>): RecyclerView.Adapter<CrimeHolder>() {
        var mCrimes: List<Crime> = crimes

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
            val layoutInflater: LayoutInflater = LayoutInflater.from(activity)
            return CrimeHolder(layoutInflater, parent)
        }

        override fun getItemCount(): Int {
            return mCrimes.size
        }

        override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
            val crime: Crime = mCrimes[position]
            holder.bind(crime)
        }

    }
}