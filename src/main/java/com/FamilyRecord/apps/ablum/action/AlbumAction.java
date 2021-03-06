package com.FamilyRecord.apps.ablum.action;

import com.FamilyRecord.abstractApps.BaseAction;
import com.FamilyRecord.apps.ablum.entity.Album;
import com.FamilyRecord.apps.ablum.service.AlbumService;
import com.FamilyRecord.apps.upload.service.CommonFileService;
import com.FamilyRecord.untils.JsonUtils;
import jdk.nashorn.internal.runtime.JSONFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yuan on 2018/3/26.
 */

@RestController
@RequestMapping("album")
public class AlbumAction extends BaseAction {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private CommonFileService commonFileService;

    @RequestMapping("insert")
    public String insert(Album album){
        boolean result = albumService.insert(album);
        return result? JsonUtils.genUpdateDataReturnJsonStr(true,"添加成功"):JsonUtils.genUpdateDataReturnJsonStr(false,"添加失败");
    }

    @RequestMapping("selectAlbumInfo")
    public String selectAlbumInfo(Album album){
        return JsonUtils.genUpdateDataReturnJsonStr(true,"查询成功",albumService.selectAlbumInfo(album));
    }

    @RequestMapping("deleteAlbum")
    public String deleteAlbum(String id){
        boolean result = albumService.deleteAlbum(id);
        if(result){
            commonFileService.deletePhotoByAlbumId(id);
        }
        return result?JsonUtils.genUpdateDataReturnJsonStr(true,"删除成功"):JsonUtils.genUpdateDataReturnJsonStr(false,"删除失败");
    }

}
