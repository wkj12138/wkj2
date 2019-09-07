import React, { Component } from "react";
import { Form, Icon, Input, Button, Checkbox } from "antd";
import ReactDOM from "react-dom";
import "./inde.css";
import "../../../node_modules/antd/dist/antd.css";
import Apply from "../apply/Apply";
const FormItem = Form.Item;
class Login extends Component {
  constructor(props) {
    super(props);
    this.state = {
      loginFlag: false
    };
    this.handlesubmit = this.handlesubmit.bind(this);
    this.Click = this.Click.bind(this);
  }
  handlesubmit() {
    // e.preventDefault();
    this.props.form.validateFields((err, values) => {
      if (!err) {
        let userinfo = this.props.form.getFieldsValue();
        // alert("账号为=" + userinfo.username);
        // alert("密码为=" + userinfo.password);
        if (userinfo.username == "123" && userinfo.password == "999") {
          alert("登录成功，正在跳转应用界面!");
          this.setState({
            loginFlag: true
          });
        } else {
          alert("账号或密码错误，请重新输入!");
          window.location.reload(true);
        }
      }
    });
  }
  Click() {}
  render() {
    const { getFieldDecorator } = this.props.form;
    if (this.state.loginFlag) {
      return <Apply />;
    }
    return (
      <Form onSubmit={this.handlesubmit} className="login-form">
        <FormItem>
          {getFieldDecorator("username", {
            rules: [
              { required: true, message: "'Please input your username!'" }
            ]
          })(
            <Input
              prefix={<Icon type="user" style={{ color: "rgba(0,0,0,.25)" }} />}
              placeholder="Username"
            />
          )}
        </FormItem>
        <FormItem>
          {getFieldDecorator("password", {
            rules: [{ required: true, message: "Please input your Password!" }]
          })(
            <Input
              prefix={<Icon type="lock" style={{ color: "rgba(0,0,0,.25)" }} />}
              type="password"
              placeholder="Password"
            />
          )}
        </FormItem>
        <FormItem>
          {getFieldDecorator("remember", {
            valuePropName: "checked",
            initialValue: true
          })(<Checkbox>Remember me</Checkbox>)}
          <a className="login-form-forgot" href="">
            Forgot password
          </a>
          <Button
            type="primary"
            htmlType="submit"
            className="login-form-button"
            // onClick={this.Click}
          >
            Log in
          </Button>
        </FormItem>
      </Form>
    );
  }
}
const WrappedNormalLoginForm = Form.create({ name: "normal_login" })(Login);

export default WrappedNormalLoginForm;
