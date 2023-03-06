<script>
import { ref } from 'vue'
import AMapLoader from "@amap/amap-jsapi-loader"
import { shallowRef, reactive } from '@vue/reactivity'

export default {
  name:"mapcomtaint",
    setup(){
        const map = shallowRef(null);
        const overlayers = reactive([]);
        const currPolygon = shallowRef(null);
        return{
            map,
            overlayers
        }

    },
    create(){

    },
    methods: {
      initMap() {
        console.log("init amap");
        AMapLoader.load({
          key: "d4bb074068a3edd7c8fe665fedb3cca4", // 申请好的Web端开发者Key，首次调用 load 时必填
          version: "1.4.15", // 指定要加载的 JSAPI 的版本，缺省时默认为 1.4.15
        })
        .then((AMap) => {
          this.map = new AMap.Map("container", {
          //设置地图容器id
          viewMode: "2D", //是否为3D地图模式
          zoom: 10, //初始化地图级别
          center: [120.074926, 23.310678], //初始化地图中心点位置
        });
        //添加插件
        AMap.plugin(["AMap.ToolBar", "AMap.Scale", "AMap.HawkEye", "AMap.PolyEditor", "AMap.MouseTool"], function () {
          //异步同时加载多个插件
          //map.addControl(new AMap.HawkEye()); //显示缩略图
          map.addControl(new AMap.Scale()); //显示当前地图中心的比例尺
          map.addControl(new AMap.PolyEditor());
          map.addControl(new AMap.MouseTool());
        });
      // // 单击
      // map.on("click", (e) => {
      //   // console.log(e);
      //   state.current_position = [e.lnglat.KL, e.lnglat.kT];
      //   state.path.push([e.lnglat.KL, e.lnglat.kT]);
      //   addMarker();

      //   addPolyLine();
      //   // 地图按照适合展示图层内数据的缩放等级展示
      //   // map.setFitView();
      // });

      // // 实例化点标记
      // function addMarker() {
      //   const marker = new AMap.Marker({
      //     icon: "//a.amap.com/jsapi_demos/static/demo-center/icons/poi-marker-default.png",
      //     position: state.current_position,
      //     offset: new AMap.Pixel(-26, -54),
      //   });
      //   marker.setMap(map);
      // }

      // // 折线
      // function addPolyLine() {
      //   const polyline = new AMap.Polyline({
      //     path: state.path,
      //     isOutline: true,
      //     outlineColor: "#ffeeff",
      //     borderWeight: 1,
      //     strokeColor: "#3366FF",
      //     strokeOpacity: 0.6,
      //     strokeWeight: 5,
      //     // 折线样式还支持 'dashed'
      //     strokeStyle: "solid",
      //     // strokeStyle是dashed时有效
      //     // strokeDasharray: [10, 5],
      //     lineJoin: "round",
      //     lineCap: "round",
      //     zIndex: 50,
      //   });
      //   map.add([polyline]);
      // }
      this.mouseTool = new AMap.MouseTool(this.map);
      //监听draw事件可获取画好的覆盖物
      console.log(this.overlayers);
      this.mouseTool.on('draw',function(e){
        //this.overlayers.push(e.obj);
        this.currPolygon = e.obj
        console.log(this.currPolygon.getPath());
      })
      console.log(this.overlayers);
    })
    .catch((e) => {
      console.log(e);
    });
  },
  drawPolygon(){
    console.log(this.overlayers.push(1));
    this.mouseTool.polygon({
      fillColor:'#00b0ff',
      strokeColor:'#80d8ff',
      //同Polygon的Option设置
      strokeColor: "#FF33FF", 
      strokeWeight: 6,
      strokeOpacity: 0.2,
      fillOpacity: 0.4,
      fillColor: '#1791fc',
      zIndex: 50,
  });

}

},
    mounted(){
      //DOM初始化完成进行地图初始化
      this.initMap();
    }
}
</script>

<template>
  <div>
    <div>
        <h3>JSAPI Vue3地图组件示例</h3>
    </div>
    <div>
      <el-button>开始绘制</el-button>
      <el-button>结束绘制</el-button>
      <el-button @click="drawPolygon()">多边形</el-button>
    </div>
    <div id="container"></div>
  </div>
</template>

<style scoped>
#container {
  height: 600px;
  width: 800%;
}

</style>