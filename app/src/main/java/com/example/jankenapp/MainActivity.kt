package com.example.jankenapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jankenapp.ui.theme.JankenAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JankenAppTheme {
                Surface (
                    modifier=Modifier.fillMaxSize(),
                    color=MaterialTheme.colorScheme.background
                    ){
                    Content()
                }
            }
        }
    }
}




@Composable
fun PlayerView(hand: Int){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text="あなたの手", fontSize = 24.sp)
        if(hand==0){
            Image(painter= painterResource(id = R.drawable.gu),//画像のリソースIDからPainterオブジェクトを取得
                contentDescription = "null",
            )
        }else if(hand==1){
            Image(painter= painterResource(id = R.drawable.choki),
                contentDescription = "null",
            )
        }else if(hand==2){
            Image(painter= painterResource(id = R.drawable.pa),
                contentDescription = "null",
            )
        }
    }
}

@Composable
fun ResultView(result:Int){
    Column {
        when(result){
            0-> Text(text="あいこ", fontSize = 32.sp,color= Color.Black)
            1-> Text(text="コンピュータの勝ち", fontSize = 32.sp,color=Color.Blue)
            2-> Text(text="あなたの勝ち", fontSize = 32.sp,color=Color.Red)
        }
    }
}

@Composable
fun ComputerView(comhand:Int){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "コンピュータの手", fontSize = 24.sp)
        when(comhand){
            0-> Image(painterResource(id = R.drawable.gu),
                contentDescription = "null",
            )
            1-> Image(painterResource(id = R.drawable.choki),
                contentDescription = "null",
            )
            2-> Image(painterResource(id = R.drawable.pa),
                contentDescription = "null",
            )
        }
    }
}

@Composable
fun Content(){
    var myhand by remember{ mutableStateOf(-1)}
    var comhand by remember{ mutableStateOf(-1)}
    var result by remember{ mutableStateOf(-1)}//この3つの変数の値が変化したときにUIを更新する必要がある
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier=Modifier.padding(32.dp)) {
        Text(text="じゃんけんアプリ", fontSize = 32.sp, fontWeight = FontWeight.Bold)
        Row(horizontalArrangement= Arrangement.spacedBy(16.dp)) {
            Button(onClick = {
                myhand=0//グーは0に対応
                comhand=(0..2).random()//0から2の乱数を生成
                result=(myhand-comhand+3)%3//0はあいこ、1は負け、2は勝ち
            }) {
                Text("グー")
            }
            Button(onClick = {
                myhand=1
                comhand=(0..2).random()
                result=(myhand-comhand+3)%3
            }) {
                Text("チョキ")
            }
            Button(onClick = {
                myhand=2
                comhand=(0..2).random()
                result=(myhand-comhand+3)%3

            }) {
                Text("パー")
            }
        }
        PlayerView(myhand)
        ComputerView(comhand)
        ResultView(result)
    }
}

//Previewアノテーションにより、エミュレータでアプリを実行することなく、コンポーザブル関数のプレビューを確誋できる
