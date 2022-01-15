package com.dsckiet.quadflix.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dsckiet.quadflix.R
import com.dsckiet.quadflix.databinding.FragmentDetailsBinding
import android.content.Intent.getIntent
import android.net.Uri
import android.os.Build
import android.text.Html
import android.text.Html.FROM_HTML_MODE_LEGACY
import android.text.Spanned
import android.text.TextUtils
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide


class DetailsFragment : Fragment() {

    lateinit var binding:FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val bundle = this.arguments
        var title=""
        var summary=""
        var image=""
        var url=""
        var score=0.0
        var language=""
        var duration=""
        var networkname=""
        if(bundle!=null){
            title = bundle.getString("title").toString()
            summary = bundle.getString("summary").toString()
            image = bundle.getString("image").toString()
            url = bundle.getString("url").toString()
            language = bundle.getString("language").toString()
            networkname = bundle.getString("networkname").toString()
            duration = bundle.getString("duration").toString()
            score = bundle.getDouble("score")
        }
        if(title!="null"){
            binding.detailstitle.text = title
        }
        if(summary!="null"){
            val text: String = summary
            val styledText: Spanned = Html.fromHtml(text, FROM_HTML_MODE_LEGACY)
            binding.detailssumm.text = styledText
        }
        if(image!="null"){
            Glide.with(requireContext()).load(image.toUri()).into(binding.detailsimg)
        }
        if(score!=0.0){
            val solution:Double = String.format("%.1f", score * 10).toDouble()
            binding.score.text = "${solution}/10"
        }
        if(language!="null"){
            binding.language.text = language
        }
        if(duration!="null"){
            binding.duration.text = "$duration min"
        }
        if(networkname!="null"){
            binding.networkname.text = networkname
        }
        binding.backbtn.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.playbtn.setOnClickListener {
            if(url!="null"){
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
            }
        }
    }

}