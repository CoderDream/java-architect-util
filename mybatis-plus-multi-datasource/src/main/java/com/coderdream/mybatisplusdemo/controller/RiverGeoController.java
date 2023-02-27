package com.coderdream.mybatisplusdemo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.coderdream.mybatisplusdemo.dto.RiverGeoReqDto;
import com.coderdream.mybatisplusdemo.dto.RiverGeoRespDto;
import com.coderdream.mybatisplusdemo.entity.RiverGeoEntity;
import com.coderdream.mybatisplusdemo.service.RiverGeoService;
import com.coderdream.mybatisplusdemo.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xulin
 * @date 2022/11/18 08:32
 */
@Slf4j
@Api(tags = "河流地理信息管理")
@RestController
@RequestMapping("api/object/data/river/geo")
public class RiverGeoController {

    @Resource
    RiverGeoService riverGeoService;

//    @ApiOperation(value = "根据id获取河流地理信息", notes = "根据id获取河流地理信息")
//    @PostMapping("/query")
//    Result<RiverGeoEntity> queryById(String id){
//        return Result.ok(riverGeoService.queryById(id));
//    }

    /**
     * 查询数据列表
     *
     * @return 列表
     */
    @ApiOperation(value = "查询数据列表", notes = "查询数据列表")
    @PostMapping("/list")
    Result<List<RiverGeoRespDto>> list(@RequestBody RiverGeoReqDto riverGeoReqDto) {
        List<RiverGeoRespDto> result = riverGeoService.list(riverGeoReqDto);
        return Result.ok(result);
    }



    @ApiOperation(value = "插入或更新河流地理信息数据", notes = "插入或更新河流地理信息数据")
    @PostMapping("/insertOrUpdateBatch")
    Result<Integer> insertOrUpdateBatch() {
        List<RiverGeoEntity> riverGeoEntities = new ArrayList<>();
//        GeometryJSON geometryJSON = new GeometryJSON();
//        GeoJSONUtil geoJSONUtil = new GeoJSONUtil();
//        String geojsonFilePah = "/templates/sxfwsxd.geojson";
//        FeatureSource featureSource= geoJSONUtil.readGeoJsonByGeojsonToFeatureSource(geojsonFilePah);
//        System.out.println(featureSource);
//
//        String resourcePath =Thread.currentThread().getContextClassLoader().getResource("").getPath();
//        File file = new File(resourcePath + geojsonFilePah);
//        System.out.println(file.exists());
//
//
////        @RequestBody List<RiverGeoEntity> riverGeoEntities
//
//        List<RiverGeoEntity> riverGeoEntities = new ArrayList<>();
//        SimpleFeatureCollection simpleFeatureCollection= geoJSONUtil.readGeoJsonByFile(resourcePath + geojsonFilePah);
//        System.out.println(simpleFeatureCollection);


        InputStream fjson = this.getClass().getResourceAsStream("/templates/sxfwsxd_3.geojson");
        //       InputStream fjson = this.getClass().getResourceAsStream("/templates/sxfwsxd.geojson");
        String tributary = new BufferedReader(new InputStreamReader(fjson)).lines().collect(Collectors.joining(System.lineSeparator()));

        JSONObject jsonObject = JSONObject.parseObject(tributary);

        System.out.println(jsonObject.toString());

        Object objectArray = jsonObject.get("features");
        RiverGeoEntity riverGeoEntity;
        if(objectArray instanceof JSONArray) {
            JSONArray features = (JSONArray) objectArray;
            int size = features.size();
            for (int i = 0; i < size; i++) {
                JSONObject item = features.getJSONObject(i);
                riverGeoEntity = new RiverGeoEntity();
                riverGeoEntity.setFeatureType(item.getString("type"));
                JSONObject properties = (JSONObject)item.get("properties");
                if(properties != null){
                    riverGeoEntity.setNAME(properties.getString("NAME"));
                    riverGeoEntity.setOBJECTID(properties.getInteger("OBJECTID"));
                    riverGeoEntity.setFNODE(properties.getInteger("FNODE"));
                    riverGeoEntity.setTNODE(properties.getInteger("TNODE"));
                    riverGeoEntity.setLPOLY(properties.getInteger("LPOLY"));
                    riverGeoEntity.setRPOLY(properties.getInteger("RPOLY"));
                    riverGeoEntity.setLENGTH(properties.getBigDecimal("LENGTH"));
                    riverGeoEntity.setHYDR(properties.getInteger("HYDR"));
                    riverGeoEntity.setHydrId(properties.getInteger("HYDR_ID"));
                    riverGeoEntity.setCODE(properties.getInteger("CODE"));
                    riverGeoEntity.setGB(properties.getInteger("GB"));
                    riverGeoEntity.setHYDC(properties.getString("HYDC"));
                    riverGeoEntity.setTN(properties.getInteger("TN"));
                    riverGeoEntity.setMAPTN(properties.getString("MAPTN"));
                    riverGeoEntity.setShapeLeng(properties.getBigDecimal("SHAPE_LENG"));
                    riverGeoEntity.setLEVEL(properties.getInteger("LEVEL"));
                    riverGeoEntity.setLENGTH(properties.getBigDecimal("LENGTH"));
                }

                JSONObject geometryInfo = (JSONObject)item.get("geometry");
                riverGeoEntity.setGeometryInfo(geometryInfo);

                riverGeoEntities.add(riverGeoEntity);
            }
        }

        int result = riverGeoService.insertOrUpdateBatch(riverGeoEntities);
        return Result.ok(result);
    }

}
