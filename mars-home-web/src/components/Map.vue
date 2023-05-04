<script setup>
import { ref } from "vue";
import AMapLoader from "@amap/amap-jsapi-loader";
import { shallowRef, reactive } from "@vue/reactivity";

const drawForm = ref({});
let map = ref(null);
let mouseTool = ref(null);
let overlayers = ref([]);
let currPolygon = ref(null);
const drawType = shallowRef("polygon");

const initMap = () => {
  console.log("init amap");
  AMapLoader.load({
    key: "d4bb074068a3edd7c8fe665fedb3cca4", // 申请好的Web端开发者Key，首次调用 load 时必填
    version: "1.4.22", // 指定要加载的 JSAPI 的版本，缺省时默认为 1.4.15
  })
    .then((AMap) => {
      map = new AMap.Map("map", {
        //设置地图容器id
        viewMode: "2D", //是否为3D地图模式
        zoom: 15, //初始化地图级别
        center: [113.939295, 22.521794], //初始化地图中心点位置
      });
      //添加插件
      AMap.plugin(
        [
          "AMap.ToolBar",
          "AMap.Scale",
          "AMap.HawkEye",
          "AMap.PolyEditor",
          "AMap.MouseTool",
        ],
        function () {
          //异步同时加载多个插件
          //map.addControl(new AMap.HawkEye()); //显示缩略图
          this.map.addControl(new AMap.Scale()); //显示当前地图中心的比例尺
          this.map.addControl(new AMap.PolyEditor());
          this.map.addControl(new AMap.MouseTool());
        }
      );
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
      mouseTool = new AMap.MouseTool(map);
      //监听draw事件可获取画好的覆盖物
      mouseTool.on("draw", function (e) {
        currPolygon = e.obj;
        overlayers.value.push(currPolygon);
      });
    })
    .catch((e) => {
      console.log(e);
    });
};
initMap();

function draw() {
  console.log(drawType);
  switch (drawType.value) {
    case "polygon":
      mouseTool.polygon({
        fillColor: "#00b0ff",
        strokeColor: "#80d8ff",
        //同Polygon的Option设置
        strokeColor: "#FF33FF",
        strokeWeight: 6,
        strokeOpacity: 0.2,
        fillOpacity: 0.4,
        fillColor: "#1791fc",
        zIndex: 50,
      });
  }
}
function clearDraw() {
  map.remove(currPolygon);
}
function stopDraw() {
  //关闭，并清除覆盖物
  mouseTool.close(true);
}
function pushPolygon() {
  console.log(currPolygon);
  console.log(currPolygon.getPath());

  this.$axios
    .get("/map/addPolygon?points=13123")
    .then((res) => {
      console.log(res.data);
    })
    .catch((e) => {
      console.log(e);
    });
}
function deployPolygon() {
  var path = [
    [113.935132, 22.52552],
    [113.945689, 22.52441],
    [113.945346, 22.516521],
    [113.93024, 22.517195],
  ];
  var polygon = new AMap.Polygon({
    path: path,
    strokeColor: "#FF33FF",
    strokeWeight: 6,
    strokeOpacity: 0.2,
    fillOpacity: 0.4,
    fillColor: "#1791fc",
    zIndex: 50,
  });
  this.map.add(polygon);
  // 缩放地图到合适的视野级别
  this.map.setFitView([polygon]);
}
function resizeMap() {
  var mapContainer = document.getElementById("map-container");
  var map = document.getElementById("map");
  map.style.width = mapContainer.offsetWidth + "px";
  map.style.height = mapContainer.offsetHeight + "px";
}
</script>

<template>
  <div class="map-container">
    <div id="map"></div>
    <div class="form-container">
      <el-form :inline="true" :model="drawForm" label-width="120px">
        <el-form-item label="绘制类型">
          <el-radio-group v-model="drawType">
            <el-radio label="polygon" />
            <el-radio label="circle" />
          </el-radio-group>
        </el-form-item>
        <el-form-item>
          <el-button @click="draw()">开始绘制</el-button>
        </el-form-item>
        <el-form-item>
          <el-button @click="stopDraw()">结束绘制</el-button>
        </el-form-item>
        <el-form-item>
          <el-button @click="clearDraw()">清除图层</el-button>
        </el-form-item>
        <el-form-item>
          <el-button @click="pushPolygon()">存储</el-button>
        </el-form-item>
        <el-form-item>
          <el-button @click="deployPolygon()">展示</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<style scoped>
.map-container {
  /* 将容器的 position 属性设置为 relative，以允许子元素使用相对于容器的位置进行定位。 */
  position: relative;
  width: 100%;
  height: 100%;
}

#map {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}

.form-container {
  position: absolute;
  top: 20px; /* 调整表单组件的位置 */
  left: 20px; /* 将表单组件放在左上角 */
  z-index: 1; /* 将表单组件放在地图上边 */
  background-color: rgba(194, 192, 192, 0.5); /* 设置半透明灰色背景色 */
  padding: 5px; /* 添加一些内边距以使表单组件更易于使用 */
}
</style>