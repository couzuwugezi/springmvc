$(document).ready(function () {
    let inLoginDiv = new Vue({
        el: '#inLoginDiv',
        data: {
            form: {
                loginname: '',
                password: ''
            },
            rules: {
                loginname: [{required: true, message: '账号不能为空'}],
                password: [{required: true, message: '密码不能为空'}]
            },
            showMsgAcc: '',
            showMsgPwd: ''
        },
        methods: {
            submitForm: function (formName) {
                let _this = this;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        _this.showMsgAcc = "";
                        _this.showMsgPwd = "";
                        $.ajax({
                            url: Lee.realPath + "/checkInfo",
                            type: "POST",
                            dataType: "json",
                            data: _this.form,
                            success: function (response) {
                                if (response.code === "1") {
                                    window.location.href = Lee.realPath + "/index";
                                } else if (response.code === "-1") {
                                    _this.showMsgAcc = response.msg;
                                } else {
                                    _this.showMsgPwd = response.msg;
                                }
                            },
                            error: function (error) {
                                console.log(error);
                            }
                        })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            }
        }
    })
});