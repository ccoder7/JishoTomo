package net.emojiparty.android.jishotomo.ui.presentation

import android.content.Context
import net.emojiparty.android.jishotomo.data.SemicolonSplit
import net.emojiparty.android.jishotomo.data.models.CrossReferencedEntry
import net.emojiparty.android.jishotomo.data.models.SenseWithCrossReferences

class SensePresenter(private val sense: SenseWithCrossReferences) {

  fun partsOfSpeechIsVisible(): Boolean {
    return sense.sense.partsOfSpeech != null
  }

  fun partsOfSpeech(context: Context): String {
    return SenseDisplay.formatPartsOfSpeech(sense.sense, AndroidResourceFetcher(context.resources), context.packageName)
  }

  fun gloss(): String {
    return SemicolonSplit.splitAndJoin(sense.sense.glosses, "\n")
  }

  fun appliesToIsVisible(): Boolean {
    return sense.sense.appliesTo != null
  }

  fun appliesTo(): String? {
    return sense.sense.appliesTo
  }

  fun crossReferencesIsVisible(): Boolean {
    return sense.crossReferences.size > 0
  }

  fun crossReferenceLinks(): List<CrossReferencedEntry> {
    return sense.crossReferences
  }
}
