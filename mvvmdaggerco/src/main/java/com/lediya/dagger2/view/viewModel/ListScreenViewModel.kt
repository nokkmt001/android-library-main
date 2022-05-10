package com.lediya.dagger2.view.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.lediya.dagger2.R
import com.lediya.dagger2.api.CountryListRepository
import com.lediya.dagger2.base.BaseViewModel
import com.lediya.dagger2.data.CountryListResponse
import com.lediya.dagger2.data.Row
import com.lediya.dagger2.utils.Constants
import com.lediya.dagger2.view.adapter.CountryAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListScreenViewModel(application: Application) : BaseViewModel(application) {
    @Inject
    lateinit var scope: CoroutineScope
    @Inject
    lateinit var repository: CountryListRepository
    private val countryData = MutableLiveData<CountryListResponse>()
    private val countryAdapter: CountryAdapter = CountryAdapter()
    val loadingVisibility: MutableLiveData<Boolean> = MutableLiveData()
    val errorTextVisibility: MutableLiveData<Boolean> = MutableLiveData()
    val errorTextData: MutableLiveData<String> = MutableLiveData()
    private var _title = MutableLiveData<String>()
    val title: MutableLiveData<String>
        get() = _title
    private lateinit var observer: Observer<CountryListResponse>
    private var context = application
    init {
        loadData()
    }

    private fun loadData() {
        loadingVisibility.value =true
        _title.value =context.getString(R.string.app_name)
        observer = Observer {
            _title.value = it.title
            countryAdapter.setItems(it.rows)
        }
        countryData.observeForever(observer)
    }
    /** checks connectivity and request data call.. */
    fun checkConnectivity(){
        if(Constants.isConnectedToNetwork(context)){
           fetchCountryData()
        }else{
            setNoInternetAvailable(true)
        }
    }
    /**
     * The method used to get country data from repository, request perform using the corountine scope and set the result  */
    private fun fetchCountryData() {
        scope.launch {
            val data = repository.getCountryData()
            if (data!=null&&!data.equals(" ") && !data.rows.isNullOrEmpty()) {
                val sortList = mutableListOf<Row>()
                data.let {
                    for (item in it.rows ) {
                        if(!item.title.isNullOrBlank()&&!item.description.isNullOrBlank()){
                            sortList.add(item)
                        }
                    }

                }
                val countryList = CountryListResponse(sortList,data.title)
                countryData.postValue(countryList)
            }
            else{
                setNoInternetAvailable(false)
            }
        }
    }
    private fun setNoInternetAvailable(connectivity:Boolean){
        loadingVisibility.value = false
        errorTextVisibility.value =true
        if(connectivity) {
            errorTextData.value = context.getString(R.string.no_internet_toast)
        }else {
            errorTextData.value = context.getString(R.string.failure_toast)
        }

    }
    /**@return country adapter  */
    fun setAdapter():CountryAdapter{
        return countryAdapter
    }
    override fun onCleared() {
        countryData.removeObserver(observer)
        super.onCleared()
        cancelAllRequests()
    }


    private fun cancelAllRequests() = scope.coroutineContext.cancel()
}

