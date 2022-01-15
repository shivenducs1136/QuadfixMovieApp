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
        if(!image.isNullOrEmpty()){
            Glide.with(requireContext()).load(image.toUri()).into(binding.detailsimg)
        }else{
            Glide.with(requireContext()).load("data:image/png;base64,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")
                .into(binding.detailsimg)
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