package com.hywx.siin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import com.hywx.siin.po.GroundStationFollow;
import com.hywx.siin.po.GroundStationPass;
import com.hywx.siin.po.SatelliteAngle;
import com.hywx.siin.po.SatelliteCover;
import com.hywx.siin.po.SatelliteRange;
import com.hywx.siin.po.SatelliteTle;

@Mapper
@Component
public interface OrbitMapper {
	// 查询所有卫星的两行根数
	@Select("SELECT SATELLITEID, TLELINE0 as tleLine0, TLELINE1 as tleLine1, TLELINE2 as tleLine2 FROM T_SATELLITE_TLE")
	List<SatelliteTle> listTle();
	
	// 根据卫星ID查询两行根数
	@Select("SELECT SATELLITEID, TLELINE0 as tleLine0, TLELINE1 as tleLine1, TLELINE2 as tleLine2 FROM T_SATELLITE_TLE WHERE SATELLITEID = #{satelliteId}")
	SatelliteTle getTle(String satelliteId);
	
	// 根据卫星ID查询卫星两行根数是否存在
	@Select("SELECT 1 FROM T_SATELLITE_TLE WHERE SATELLITEID = #{satelliteId} LIMIT 1")
	Boolean existTle(String satelliteId);
	
	// 插入卫星两行根数到数据库
	@Insert("INSERT OR IGNORE INTO T_SATELLITE_TLE(SATELLITEID, TLELINE0, TLELINE1, TLELINE2) "
			+ "VALUES(#{satelliteId}, #{tleLine0}, #{tleLine1}, #{tleLine2})")
    int insertTle(@Param("satelliteId") String satelliteId, @Param("tleLine0") String tleLine0, @Param("tleLine1") String tleLine1, @Param("tleLine2") String tleLine2);
	
	// 根据卫星ID删除卫星两行根数
	@Delete("DELETE FROM T_SATELLITE_TLE WHERE SATELLITEID = #{satelliteId}")  
    int deleteTle(@Param("satelliteId") String satelliteId);
	
	// 根据卫星ID更新卫星两行根数
	@Update("UPDATE T_SATELLITE_TLE SET TLELINE0=#{tleLine0}, TLELINE1=#{tleLine1}, TLELINE2=#{tleLine2} WHERE SATELLITEID = #{satelliteId}")  
    int updateTle(@Param("tleLine0") String tleLine0, @Param("tleLine1") String tleLine1, @Param("tleLine2") String tleLine2, @Param("satelliteId") String satelliteId);
	
	
		
	/**************************外测测距测速*******************************************/
	// 查询外测测距测速数据
	@Select("SELECT EPOCH, RANGE, RANGERATE FROM T_SATELLITE_RANGE")
	List<SatelliteRange> listRanges();
	
	// 清空表数据
    @Delete("DELETE FROM T_SATELLITE_RANGE")
    int deleteRange();
    
    // 插入1条外测测距测速数据
    @Insert("INSERT INTO T_SATELLITE_RANGE(EPOCH, RANGE, RANGERATE) VALUES(#{epoch}, #{range}, #{rangeRate})")
    int insertRange(SatelliteRange range);
	
	// 批量插入外测测距测速数据
	@Insert({
		"<script>",
		 "insert into T_SATELLITE_RANGE(EPOCH, RANGE, RANGERATE) values ",
		 "<foreach collection='list' item='item' index='index' separator=','>",
		 "(#{item.epoch}, #{item.range}, #{item.rangeRate})",
		 "</foreach>",
		 "</script>"
	})
	int insertBatchRange(@Param(value = "list") List<SatelliteRange> list);
	
	
	/**************************外测测角*******************************************/
	// 查询外测测角数据
	@Select("SELECT EPOCH, AZIMUTH, ELEVATION FROM T_SATELLITE_ANGLE")
	List<SatelliteAngle> listAngles();
	
	// 清空表数据
    @Delete("DELETE FROM T_SATELLITE_ANGLE")
    int deleteAngle();
    
    // 插入1条外测测角数据
    @Insert("INSERT INTO T_SATELLITE_ANGLE(EPOCH, AZIMUTH, ELEVATION) VALUES(#{epoch}, #{azimuth}, #{elevation})")
    int insertAngle(SatelliteAngle angle);
	
	// 批量插入外测测角数据
	@Insert({
		"<script>",
		 "insert into T_SATELLITE_ANGLE(EPOCH, AZIMUTH, ELEVATION) values ",
		 "<foreach collection='list' item='item' index='index' separator=','>",
		 "(#{item.epoch}, #{item.azimuth}, #{item.elevation})",
		 "</foreach>",
		 "</script>"
	})
	int insertBatchAngle(@Param(value = "list") List<SatelliteAngle> list);
	
	/**************************卫星覆盖半径(含星下点)*******************************************/
	// 查询卫星覆盖半径数据
	@Select("SELECT EPOCH, LNG, LAT, ALT, RADIUS, GROUNDSTATIONS FROM T_SATELLITE_COVER")
	List<SatelliteCover> listCovers();
	
	// 清空表数据
    @Delete("DELETE FROM T_SATELLITE_COVER")
    int deleteCover();
    
    // 插入1条卫星覆盖半径数据
    @Insert("INSERT INTO T_SATELLITE_COVER(EPOCH, LNG, LAT, ALT, RADIUS, GROUNDSTATIONS) VALUES(#{epoch}, #{lng}, #{lat}, #{alt}, #{radius}, #{groundStations})")
    int insertCover(SatelliteCover cover);
	
	// 批量插入卫星覆盖半径数据
	@Insert({
		"<script>",
		 "insert into T_SATELLITE_COVER(EPOCH, LNG, LAT, ALT, RADIUS, GROUNDSTATIONS) values ",
		 "<foreach collection='list' item='item' index='index' separator=','>",
		 "(#{item.epoch}, #{item.lng}, #{item.lat}, #{item.alt}, #{item.radius}, #{item.groundStations})",
		 "</foreach>",
		 "</script>"
	})
	int insertBatchCover(@Param(value = "list") List<SatelliteCover> list);
	
	
	/**************************卫星过境*******************************************/
	// 查询卫星过境数据
	@Select("SELECT STARTTIME, ENDTIME, DURATION, AOSAZIMUTH, MAXELEVATION, LOSAZIMUTH FROM T_GROUNDSTATION_PASS")
	List<GroundStationPass> listPasses();
	
	// 清空表数据
    @Delete("DELETE FROM T_GROUNDSTATION_PASS")
    int deletePass();
    
    // 插入1条卫星过境数据
    @Insert("INSERT INTO T_GROUNDSTATION_PASS(STARTTIME, ENDTIME, DURATION, AOSAZIMUTH, MAXELEVATION, LOSAZIMUTH) VALUES(#{startTime}, #{endTime}, #{duration}, #{aosAzimuth}, #{maxElevation}, #{losAzimuth})")
    int insertPass(GroundStationPass pass);
	
	// 批量插入卫星过境数据
	@Insert({
		"<script>",
		 "insert into T_GROUNDSTATION_PASS(STARTTIME, ENDTIME, DURATION, AOSAZIMUTH, MAXELEVATION, LOSAZIMUTH) values ",
		 "<foreach collection='list' item='item' index='index' separator=','>",
		 "(#{item.startTime}, #{item.endTime}, #{item.duration}, #{item.aosAzimuth}, #{item.maxElevation}, #{item.losAzimuth})",
		 "</foreach>",
		 "</script>"
	})
	int insertBatchPass(@Param(value = "list") List<GroundStationPass> list);
	
	
	/**************************信关站跟踪*******************************************/
	// 查询信关站跟踪数据
	@Select("SELECT EPOCH, AZIMUTH, ELEVATION FROM T_GROUNDSTATION_FOLLOW")
	List<GroundStationFollow> listFollows();
	
	// 清空表数据
    @Delete("DELETE FROM T_GROUNDSTATION_FOLLOW")
    int deleteFollow();
    
    // 插入1条信关站跟踪数据
    @Insert("INSERT INTO T_GROUNDSTATION_FOLLOW(EPOCH, AZIMUTH, ELEVATION) VALUES(#{epoch}, #{azimuth}, #{elevation})")
    int insertFollow(GroundStationFollow follow);
	
	// 批量插入信关站跟踪数据
	@Insert({
		"<script>",
		 "insert into T_GROUNDSTATION_FOLLOW(EPOCH, AZIMUTH, ELEVATION) values ",
		 "<foreach collection='list' item='item' index='index' separator=','>",
		 "(#{item.epoch}, #{item.azimuth}, #{item.elevation})",
		 "</foreach>",
		 "</script>"
	})
	int insertBatchFollow(@Param(value = "list") List<GroundStationFollow> list);
	

}
