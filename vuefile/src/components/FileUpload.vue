<template>
    <el-upload
            class="upload-demo"
            ref="upload"
            :action="uploadUrl"
            :multiple="isMul"
            :on-preview="handlePreview"
            :on-remove="handleRemove"
            :on-success="onSuccess"
            :on-error="onError"
            :file-list="fileList"
            :auto-upload="false">
        <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
        <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button>
        <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
    </el-upload>
</template>

<script>
export default {
    data () {
        return {
            fileList: [],
            isMul: true,
            uploadUrl: this.$axios.defaults.baseURL+'/upload'
        }
    },
    methods: {
        submitUpload() {
        this.$refs.upload.submit();
        },
        handleRemove(file, fileList) {
            console.log(file, fileList);
        },
        handlePreview(file) {
            console.log(file);
        },
        onSuccess (response, file, fileList) {
            console.log(response)
        },
        onError (err, file, fileList) {
            console.log('error')
        }
    }
}
</script>

<style>

</style>