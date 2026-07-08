package com.zyyme.urlschemetest

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zyyme.urlschemetest.ui.theme.UrlSchemeTestTheme

class MainActivity : ComponentActivity() {
    private var openedUrl by mutableStateOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        openedUrl = intent.dataString.orEmpty()
        setContent {
            UrlSchemeTestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UrlContent(
                        url = openedUrl,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        openedUrl = intent.dataString.orEmpty()
    }
}

@Composable
fun UrlContent(url: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = if (url.isBlank()) "完整 URL: 未通过 URL Scheme 打开" else "完整 URL: $url")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    UrlContent(
        url = "sparkle://hello?name=$name",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UrlSchemeTestTheme {
        Greeting("Android")
    }
}
