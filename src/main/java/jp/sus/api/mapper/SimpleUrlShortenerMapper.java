package jp.sus.api.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import jp.sus.api.model.UrlShortenerData;

/**
 * Table CRUD operation using MyBatis.
 */
@Mapper
public interface SimpleUrlShortenerMapper {
  /** Retrieve all data. */
  @Select("SELECT id, original_url, expire, created_timestamp FROM url_shortener_data")
  public List<UrlShortenerData> all();

  /** Retrieve single data. */
  @Select("SELECT id, original_url, expire, created_timestamp"
      + " FROM url_shortener_data WHERE id=#{id}")
  public UrlShortenerData findById(String id);

  /** Data registration. */
  @Insert("INSERT INTO url_shortener_data(id, original_url, expire, created_timestamp)"
      + "VALUES (#{id}, #{originalUrl}, #{expire}, #{createdTimestamp});")
  public Boolean create(UrlShortenerData sus);

  // TODO not implemented.
  // public Sus update(Sus sus);

  // TODO not implemented.
  // public Sus delete(String id);
}
