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
            },
            openForm: function () {
                dialog.form = {};
                dialog.show = true;

            },
            clearPwdMsg: function () {
                this.showMsgPwd = '';
            },
            clearAccMsg: function () {
                this.showMsgAcc = '';
            }
        }
    });

    let dialog = new Vue({
        el: "#dialog",
        data: {
            show: false,
            form: {},
            sexOptions: [{
                value: '1',
                label: '男'
            }, {
                value: '0',
                label: '女'
            }],
            rules: {
                loginName: [
                    {required: true, message: '该项不能为空', trigger: 'blur'},
                    {min: 1, max: 20, message: '长度在 1 到 50 个字符', trigger: 'blur'},
                    {
                        validator: function (rule, value, callback) {
                            let zzjgdmRegex = /^(\w|[0-9])*$/;
                            if (!zzjgdmRegex.test(value)) {
                                callback(new Error('只能输入英文和数字！'))
                            } else {
                                callback();
                            }
                        },
                        trigger: 'blur'
                    }
                ],
                realName:
                    [
                        {required: true, message: '该项不能为空', trigger: 'blur'},
                        {min: 1, max: 20, message: '长度在 1 到 50 个字符', trigger: 'blur'},
                        {
                            validator: function (rule, value, callback) {
                                let nameRegex = /^[\u4E00-\u9FFFa-zA-Z]+$/;
                                if (!nameRegex.test(value)) {
                                    callback(new Error('只能输入中文英文！'))
                                } else {
                                    callback();
                                }
                            },
                            trigger: 'blur'
                        }
                    ],
                phone:
                    [
                        {required: true, message: '该项不能为空', trigger: 'blur'},
                        {min: 1, max: 11, message: '长度在 1 到 11 个字符', trigger: 'blur'},
                        {
                            validator: function (rule, value, callback) {
                                let telRegex = /^([0-9]|[\-])*$/;
                                if (!telRegex.test(value)) {
                                    callback(new Error('只能输入数字和-！'))
                                } else {
                                    callback();
                                }
                            },
                            trigger: 'blur'
                        }
                    ],
                sex:
                    [
                        {required: true, message: '该项不能为空', trigger: 'blur'}
                    ],
                loginPassword:
                    [
                        {required: true, message: '该项不能为空', trigger: 'blur'},
                        {min: 1, max: 20, message: '长度在 1 到 50 个字符', trigger: 'blur'}
                    ],
            }
        },
        methods: {
            submitForm: function (formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        $.ajax({
                            url: Lee.realPath + "/register",
                            type: "POST",
                            dataType: "json",
                            data: JSON.stringify(dialog.form),
                            async: false,
                            contentType: "application/json",
                            success: function (response) {
                                debugger;
                                dialog.form = {};
                                dialog.show = false;
                                dialog.$alert(response.msg);
                            },
                            error: function (error) {
                                dialog.$alert("系统异常!");
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
    });
});