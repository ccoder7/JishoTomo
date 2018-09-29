package net.emojiparty.android.jishotomo.data.room;

import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import java.util.List;
import net.emojiparty.android.jishotomo.data.models.EntryWithAllSenses;
import net.emojiparty.android.jishotomo.data.models.SearchResultEntry;

@Dao
public interface EntryDao {
  @Update
  void updateEntry(Entry entry);

  @Query("SELECT * FROM entries WHERE entries.id = :id LIMIT 1")
  LiveData<EntryWithAllSenses> getEntryById(int id);

  @Query("SELECT id, primary_kanji, primary_reading FROM entries ORDER BY id ASC")
  DataSource.Factory<Integer, SearchResultEntry> browse();

  @Query("SELECT id, primary_kanji, primary_reading FROM entries WHERE primary_kanji LIKE :term")
  DataSource.Factory<Integer, SearchResultEntry> search(String term);

  @Query("SELECT id, primary_kanji, primary_reading FROM entries WHERE favorited = 1")
  DataSource.Factory<Integer, SearchResultEntry> getFavorites();

  @Query("SELECT * FROM entries WHERE favorited = 1")
  List<EntryWithAllSenses> getAllFavorites();

  @Query("SELECT id, primary_kanji, primary_reading FROM entries WHERE jlpt_level = :level ORDER BY id ASC")
  DataSource.Factory<Integer, SearchResultEntry> findByJlptLevel(Integer level);

  // WIDGET

  @Query("SELECT id, primary_kanji, primary_reading FROM entries WHERE jlpt_level = :level ORDER BY id ASC LIMIT 1 OFFSET :offset")
  SearchResultEntry randomByJlptLevel(Integer level, int offset);

  @Query("SELECT COUNT(id) FROM entries WHERE jlpt_level = :level")
  int getJlptLevelCount(Integer level);
}
