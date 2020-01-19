<template>
  <el-form>
      <el-form-item>
          <el-input type="text" v-model="downloadCode" placeholder="请输入下载码"></el-input>
      </el-form-item>
      <el-form-item>
          <el-button type="primary" style="width: 100%" @click="download">下载</el-button>
      </el-form-item>
  </el-form>
</template>

<script>
export default {
    data() {
        return {
            downloadCode: ''
        }
        
    },
    methods: {
        download() {
            var _this = this;
            var $downloadCode = this.$data.downloadCode;
            var url = this.$axios.defaults.baseURL+'/download/'+$downloadCode
            let xhr = new window.XMLHttpRequest();
            xhr.onreadystatechange=function(){
                if(xhr.readyState == 4 && xhr.status == 200){
                    var text = xhr.responseText;
                    text = eval(text.toLowerCase());
                    console.log(text)
                    console.log(typeof text)
                    //如果验证下载码有效，则可以进行下载
                    if(text == 105) {
                        window.location.href = url
                        _this.$data.downloadCode = ''
                    }else {
                        alert("没有找到对应文件！");
                    }
                }
            }
            xhr.open("post",this.$axios.defaults.baseURL+"/downloadVerify",true);
            xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
            xhr.send("downloadCode="+$downloadCode);
        }
    }
}
</script>

<style>

</style>