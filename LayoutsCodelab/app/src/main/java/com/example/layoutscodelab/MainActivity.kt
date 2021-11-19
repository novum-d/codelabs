package com.example.layoutscodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.RemeasurementModifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.layoutscodelab.ui.theme.LayoutsCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutsCodelabTheme {
                LayoutsCodelab()
            }
        }
    }
}


/** Composable ------------------------------------------------------------- */


@Composable
fun LayoutsCodelab() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "LayoutsCodelab")
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.Favorite, contentDescription = null)
                    }
                }

            )
        }
    ) { innerPadding ->
        BodyContent(Modifier.padding(innerPadding).padding(8.dp))
    }
}

@Composable
fun BodyContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = "Hi there!")
        Text(text = "Thanks for going through the Layouts codelab")
    }
}


@Composable
fun PhotographerCard(modifier: Modifier = Modifier) {
    Row(
        modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp)) // 行の角を丸める
            .background(MaterialTheme.colors.surface) // 背景色を変更
            .clickable(onClick = { })
            .padding(16.dp) // clickableの定義のあとにpaddingを追加することでclickable領域を広げることができる
    ) {
        Surface(
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {
        }
        Column(
            modifier = Modifier
                .padding(start = 8.dp) // startにdpを設定
                .align(Alignment.CenterVertically) // 縦方向に中央揃え
        ) {
            Text("Alfred Sisley", fontWeight = FontWeight.Bold)
            // LocalContentAlpha is defining opacity level of its children
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text("3 minutes ago", style = MaterialTheme.typography.body2)
            }
        }
    }
}

/** Preview ------------------------------------------------------------- */

@Preview(showBackground = true)
@Composable
fun PhotographerCardPreview() {
    LayoutsCodelabTheme {
        PhotographerCard()
    }
}

@Preview
@Composable
fun LayoutCodelabPreview() {
    LayoutsCodelabTheme {
        LayoutsCodelab()
    }
}
