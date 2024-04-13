package com.example.lantsev

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.lantsev.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val post = Post(
            id = 1,
            author = "ГБПОУ ВО \"БТПИТ\"",
            content = "ЛИДЕР ДВИЖЕНИЯ ПЕРВЫХ\n" +
                    "29 марта студент 2 курса Бараненко Максим Борисоглебского техникума промышленных и информационных технологий совместно с советником директора по воспитанию Алехиной Светланой приняли участие в территориальном этапе регионального конкурса «Лидер Движения Первых Воронежской области», который проходил на базе МОУ Новохоперской СОШ N2.\n" +
                    "Ребята проходили испытания по следующим темам: «Эрудит», «Оратор», «Работа в команде».\n" +
                    "По итогам работы победителем в номинации «Лидер Движения Первых в профессиональных образовательных организациях в возрасте от 16 до 18 лет» признан Бараненко Максим.\n" +
                    "Максим будет представлять наш техникум на региональном этапе конкурса.",
            published = "30 мар в 13:13",
            likes = 999999,
            share = 999,
            likedByMe = false
        )
        with(binding){
            author.text = post.author
            published.text = post.published
            textt.text = post.content
            textlike.text = post.likes.toString()
            textshare.text = post.share.toString()
            if (post.likedByMe) {
                like?.setImageResource(R.drawable.ic_liked_24)
            }
            share?.setOnClickListener {
                post.share++
                textshare.text = post.share.toString()
                when {
                    post.share<1000 ->textshare.text =post.share.toString()
                    post.share in 1000..999999 ->textshare.text ="${post.share/1000}K"
                    else->textshare.text =String.format("%.1fM",post.share.toDouble()/1000000)
                }

            }

            like?.setOnClickListener {
                post.likedByMe = !post.likedByMe
                like.setImageResource(
                    if (post.likedByMe) R.drawable.ic_liked_24
                    else R.drawable.ic_like_24
                )
                if (post.likedByMe) post.likes++ else post.likes--
                textlike.text = post.likes.toString()
                when {
                    post.likes in 1000..999999 ->textlike.text ="${post.likes/1000}K"
                    post.likes<1000->textlike.text =post.likes.toString()
                    else->textlike.text =String.format("%.1fM",post.likes.toDouble()/1000000)
                }
            }
        }
    }

    data class Post(
        val id: Int,
        val author: String,
        val content: String,
        val published: String,
        var likes: Int,
        var share: Int,
        var likedByMe: Boolean = false
    )
}