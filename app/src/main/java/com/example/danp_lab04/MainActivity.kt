package com.example.danp_lab04

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.danp_lab04.adapters.CountryAdapter
import com.example.danp_lab04.paging.CountryViewModel
import com.example.danp_lab04.ui.theme.DANP_LAB04Theme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DANP_LAB04Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context = LocalContext.current
                    CountryScreen(context = context)
                }
            }
        }
        setContentView(R.layout.activity_main)

        val viewModel by viewModels<CountryViewModel>()
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val pagingAdapter = CountryAdapter()

        recyclerView.adapter = pagingAdapter
        recyclerView.apply {
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }

        lifecycleScope.launch {
            viewModel.items().collectLatest { pageData ->
                pagingAdapter.submitData(pageData)
            }
        }
    }
}

@Composable
fun CountryScreen (
    context: Context,
    viewModel: CountryViewModel = hiltViewModel()
) {
    viewModel.addCountriesFromJason(context)
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DANP_LAB04Theme {
    }
}