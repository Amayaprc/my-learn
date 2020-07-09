package com.prc.springbootcanal.canal;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.xpand.starter.canal.annotation.CanalEventListener;
import com.xpand.starter.canal.annotation.InsertListenPoint;
import com.xpand.starter.canal.annotation.ListenPoint;

@CanalEventListener //Canal数据监听配置
public class CanalDataEventListener {


    @ListenPoint(destination = "example",schema = "xuexi",table = {"book"},eventType = CanalEntry.EventType.INSERT)
    public void onEventEventCustomInsert(CanalEntry.EventType eventType,CanalEntry.RowData rowData){
        //1,增加，2修改，3删除,通过eventType确定操作
        int code =eventType.getNumber();

        //2.获取变更数据的主键
        String id = getColumn(rowData,"id");
        System.out.println("变更数据主键:" + id);
    }


    /***
     * 增加数据监听
     * eventType:操作类型，增删改
     * rowData:操作的数据
     */
    @InsertListenPoint  //增加数据节点监听
    public void onEventInsert(CanalEntry.EventType eventType,CanalEntry.RowData rowData){
        //获取所有列操作后的信息
        //rowData.getAfterColumnsList();
        //获取所有列操作之前的信息
        //rowData.getBeforeColumnsList();

        //循环操作数据
        for (CanalEntry.Column column : rowData.getAfterColumnsList()) {
            System.out.println("增加---->列名：" + column.getName() + "=" + column.getValue());
        }
    }


    /***
     * 修改数据监听
     */
//    @UpdateListenPoint  //修改数据节点监听
    public void onEventUpdate(CanalEntry.EventType eventType,CanalEntry.RowData rowData){
        //rowData.getAfterColumnsList() :获取所有列操作后的信息
        //rowData.getBeforeColumnsList();  获取所有列操作之前的信息

        //循环操作数据
        for (CanalEntry.Column column : rowData.getAfterColumnsList()) {
            System.out.println("修改---->列名：" + column.getName() + "=" + column.getValue());
        }
    }


    /****
     * 删除数据监听
     */
//    @DeleteListenPoint  //删除数据节点监听
    public void onEventDelete(CanalEntry.EventType eventType,CanalEntry.RowData rowData){
        //rowData.getAfterColumnsList() :获取所有列操作后的信息
        //rowData.getBeforeColumnsList();  获取所有列操作之前的信息

        //循环操作数据
        for (CanalEntry.Column column : rowData.getBeforeColumnsList()) {
            System.out.println("删除---->列名：" + column.getName() + "=" + column.getValue());
        }
    }

    /****
     * 自定义监听
     */
//    @ListenPoint(destination = "example",schema = "changgou_content",table = {"tb_content","tb_content_category"},eventType = CanalEntry.EventType.UPDATE)
    public void onEventCustomUpdate(CanalEntry.EventType eventType,CanalEntry.RowData rowData){
        //循环操作数据
        for (CanalEntry.Column column : rowData.getAfterColumnsList()) {
            System.out.println("修改--AAAA-->列名：" + column.getName() + "=" + column.getValue());
        }
    }


    /***
     * 获取指定列的值
     * @param rowData
     * @param columnName
     * @return
     */
    public String getColumn(CanalEntry.RowData rowData,String columnName){
        //操作后的值
        for (CanalEntry.Column column : rowData.getAfterColumnsList()) {
            String name = column.getName();
            if(name.equalsIgnoreCase(columnName)){
                return column.getValue();
            }
        }

        //操作前的值
        for (CanalEntry.Column column : rowData.getBeforeColumnsList()) {
            String name = column.getName();
            if(name.equalsIgnoreCase(columnName)){
                return column.getValue();
            }
        }
        return null;
    }
}
