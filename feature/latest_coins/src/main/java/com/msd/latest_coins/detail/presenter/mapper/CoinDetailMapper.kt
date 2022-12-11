package com.msd.latest_coins.detail.presenter.mapper

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.msd.coin_details.model.CoinDetailDomain
import com.msd.coin_details.model.UrlsDomain
import com.msd.latest_coins.R
import com.msd.latest_coins.detail.presenter.model.CoinDetail
import java.text.SimpleDateFormat
import java.util.*

object CoinDetailMapper {

    private const val DATE_ADDED_INPUT_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    private const val DATE_ADDED_OUTPUT_FORMAT = "dd MMMM yyyy"

    fun CoinDetailDomain.toPresentation(): CoinDetail {
        return CoinDetail(
            name = name,
            symbol = symbol,
            description = description,
            dateAdded = dateAdded.formatDateAdded(),
            tagNames = tagNames,
            technicalButtons = urls.toTechnicalButtons(),
            urlButtons = urls.toUrlButtons()
        )
    }

    private fun String.formatDateAdded(): String {
        val date = SimpleDateFormat(DATE_ADDED_INPUT_FORMAT, Locale.getDefault()).parse(this)
        val simpleDateFormat = SimpleDateFormat(DATE_ADDED_OUTPUT_FORMAT, Locale.getDefault())
        return date?.let { simpleDateFormat.format(date) }.orEmpty()
    }

    private fun UrlsDomain.toTechnicalButtons(): List<CoinDetail.TechnicalButtons> {
        return mutableListOf<CoinDetail.TechnicalButtons>().apply {
            addIfValid(R.drawable.ic_source_code, R.string.details_source_code_button, sourceCode)
            addIfValid(
                R.drawable.ic_technical_doc,
                R.string.details_technical_docs_button,
                technicalDoc
            )
        }
    }

    private fun MutableList<CoinDetail.TechnicalButtons>.addIfValid(
        @DrawableRes icon: Int,
        @StringRes label: Int,
        url: String
    ) {
        url.takeUnless { it.isEmpty() }?.let { add(CoinDetail.TechnicalButtons(icon, label, url)) }
    }

    private fun UrlsDomain.toUrlButtons(): List<CoinDetail.UrlButton> {
        return mutableListOf<CoinDetail.UrlButton>().apply {
            addIfValid(R.drawable.ic_twitter, twitter)
            addIfValid(R.drawable.ic_announcement, announcement)
            addIfValid(R.drawable.ic_browser, explorer)
            addIfValid(R.drawable.ic_message, messageBoard)
            addIfValid(R.drawable.ic_chat, chat)
            addIfValid(R.drawable.ic_reddit, reddit)
        }
    }

    private fun MutableList<CoinDetail.UrlButton>.addIfValid(@DrawableRes icon: Int, url: String) {
        url.takeUnless { it.isEmpty() }?.let { add(CoinDetail.UrlButton(icon, url)) }
    }
}
