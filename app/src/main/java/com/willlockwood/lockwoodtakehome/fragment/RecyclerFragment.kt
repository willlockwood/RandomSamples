package com.willlockwood.lockwoodtakehome.fragment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.willlockwood.lockwoodtakehome.R
import com.willlockwood.lockwoodtakehome.adapter.LockwoodRecyclerAdapter
import com.willlockwood.lockwoodtakehome.data.api.LockwoodApiService
import com.willlockwood.lockwoodtakehome.data.api.LockwoodResponse
import com.willlockwood.lockwoodtakehome.viewmodel.LockwoodVM
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_recycler_view.*

class RecyclerFragment : Fragment() {

    private val lockwoodVM: LockwoodVM by viewModels()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapterLockwood: LockwoodRecyclerAdapter
    private lateinit var compositeDisposable: CompositeDisposable

    private val lockwoodApiService by lazy { LockwoodApiService.create() }
    private val lockwoodApiServiceRx by lazy { LockwoodApiService.createRx() }

//    private val gson = GsonBuilder().create()
//    private val retrofit = Retrofit.Builder()
//        .baseUrl(MainActivity.BASE_URL)
//        .addConverterFactory(GsonConverterFactory.create(gson))
//        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        compositeDisposable = CompositeDisposable()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_recycler_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()

        observeLockwoods()

        apiCallRxJava()
    }

    private fun observeLockwoods() {
        lockwoodVM.allLockwoods().observe(viewLifecycleOwner, Observer {
            adapterLockwood.setLockwoods(it)
        })
    }

    private fun setUpRecyclerView() {
        recyclerView = recycler_view
        adapterLockwood = LockwoodRecyclerAdapter(requireContext())
        recyclerView.adapter = adapterLockwood
        adapterLockwood.setOnItemClickListener(object : LockwoodRecyclerAdapter.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                Toast.makeText(requireContext(), "Item clicked", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun handleError(throwable: Throwable) {
        Log.e("PageFragment", "error", throwable)
    }

    fun handleResponse(response: LockwoodResponse) {
        lockwoodVM.insertLockwoods(
            response.hits
        )
    }

    private fun apiCallRxJava() {

        compositeDisposable.add(lockwoodApiServiceRx.getRxPhotos()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::handleResponse) {
                handleError(it)
            }
        )

//        val call = lockwoodApiService.getPhotos(MainActivity.photos_per_page, MainActivity.API_KEY)
//        call.enqueue(
//            object : Callback<LockwoodResponse> {
//                override fun onResponse(call: Call<LockwoodResponse>, response: Response<LockwoodResponse>) {
//                    when (response.code()) {
//                        200 -> {
//                            val blah = response
//                            val body = response.body()
//                            val likes = body?.likes
//                            lockwoodVM.insertLockwoods(response.body()?.likes!!)
//                        }
//                        429 -> { }
//                        else -> { }
//                    }
//                }
//                override fun onFailure(call: Call<LockwoodResponse>, t: Throwable) {}
//            }
//        )
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

}
