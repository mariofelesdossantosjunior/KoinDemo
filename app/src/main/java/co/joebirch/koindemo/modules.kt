package co.joebirch.koindemo

import co.joebirch.koindemo.data.DataRepository
import co.joebirch.koindemo.data.DataRepositoryFactory
import co.joebirch.koindemo.data.LocalDataRepository
import co.joebirch.koindemo.data.RemoteDataRepository
import co.joebirch.koindemo.presentation.CurrenciesViewModel
import com.google.gson.Gson
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val applicationModule = module {
    single { Gson() }

    factory<DataRepository>("local") { LocalDataRepository(get()) }
    factory<DataRepository>("remote") { RemoteDataRepository() }

    factory { DataRepositoryFactory(get("local"), get("remote")) }

    factory { CurrenciesAdapter() }
    viewModel { (jsonString: String) -> CurrenciesViewModel(get(), jsonString) }
}