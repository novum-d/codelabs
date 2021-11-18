package com.example.basicscodelab

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basicscodelab.ui.theme.BasicsCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicsCodelabTheme {
                Myapp()
            }
        }
    }
}


/** Composable ------------------------------------------------------------- */

@Composable
private fun Myapp() {
    /** State hoisting */
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
    if (shouldShowOnboarding) {
        OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
    } else {
        Greetings()
    }
}

@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit) {

    Surface {
        Column(
            modifier = Modifier.fillMaxSize(), // 余白を最大まで要素で埋める
            verticalArrangement = Arrangement.Center, // 縦方向で中央に寄せる
            horizontalAlignment = Alignment.CenterHorizontally // 横方向で中央に寄せる
        ) {
            Text("Welcome to the Basics Codelab!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked
            ) {
                Text("Continue")
            }
        }
    }
}

@Composable
private fun Greetings(names: List<String> = List(1000) { "$it" }) {
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            Greeting(name = name)
        }
    }
}

@Composable
fun Greeting(name: String) {
    Card(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        CardContent(name = name)
    }
}

@Composable
private fun CardContent(name: String) {
    // 値を保持し、recomposeをトリガー
    var expanded by remember { mutableStateOf(false) }

    // val extraPadding by animateDpAsState(
    //     targetValue = if (expanded) 48.dp else 0.dp,
    //     animationSpec = spring(
    //         dampingRatio = Spring.DampingRatioMediumBouncy, // dampingRatio 減衰の比率
    //         stiffness = Spring.StiffnessLow // stiffness 剛性
    //     )
    // )

    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy, // dampingRatio 減衰の比率
                    stiffness = Spring.StiffnessLow // stiffness 剛性
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f) // 空の要素が使用可能なスペースを埋める
                .padding(12.dp)
            // paddingが負にならないようにする
            // .padding(bottom = extraPadding.coerceAtLeast(0.dp)) // coerce at least : 少なくとも強制する
        ) {
            Text(text = "Hello,")
            Text(
                text = name,
                style = MaterialTheme.typography.h4.copy( // 事前定義されたスタイルを変更
                    fontWeight = FontWeight.ExtraBold
                )
            )
            if (expanded) {
                Text(
                    text = ("Composem ipsum color sit lazy, padding theme elit, sed do bouncy.").repeat(4)
                )
            }
        }

        IconButton(onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Filled.ExpandLess else Filled.ExpandMore, // ベクター画像
                contentDescription = if (expanded) stringResource(R.string.show_less) else stringResource(
                    R.string.show_more
                )
            )
        }

        // OutlinedButton(
        //     onClick = { expanded = !expanded }
        // ) {
        //     Text(if (expanded) stringResource(R.string.show_less) else stringResource(R.string.show_more))
        // }
    }
}


/** Preview ------------------------------------------------------------- */

@Preview(
    showBackground = true,
    widthDp = 320,// プレビューの幅を設定
    uiMode = UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark", // プレビュー名
)
@Preview(
    showBackground = true, widthDp = 320 // プレビューの幅を設定
)
@Composable
fun DefaultPreview() {
    BasicsCodelabTheme {
        Greetings()
    }
}


@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    BasicsCodelabTheme {
        OnboardingScreen(onContinueClicked = {}) // プレビューには空のラムダを渡す
    }
}


@Preview(showBackground = true, widthDp = 320)
@Composable
fun GreetingPreview() {
    BasicsCodelabTheme {
        Greeting(name = "1")
    }
}