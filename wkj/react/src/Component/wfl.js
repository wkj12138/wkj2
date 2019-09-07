import React, { Component } from "react";
import axios from "axios";
import { Card, Table, Button, Modal, Input } from "antd";
import "./css/wfl.css";
class Wfl extends Component {
  constructor(props) {
    super(props);
    this.state = {
      lists: "",
      visible: false
    };
    this.GetDate = this.GetDate.bind(this);
    this.SendMail = this.SendMail.bind(this);
    this.showModal = this.showModal.bind(this);
    this.hideModal = this.hideModal.bind(this);
  }

  GetDate() {
    var city = document.getElementById("city").value;
    console.log(city);
    axios.get("http://localhost:8099/weather").then(respon => {
      console.log(respon);
      console.log(respon.data);
      if (city == "武汉") {
        this.setState({
          lists: city + "天气情况如下：\n" + respon.data
        });
      } else {
        this.setState({
          lists: "暂时无" + city + "天气情况"
        });
      }
    });
  }
  showModal() {
    this.setState({
      visible: true
    });
  }
  hideModal() {
    this.setState({
      visible: false
    });
  }
  SendMail() {
    let title = document.getElementById("title").value;
    let sendU = document.getElementById("sendU").value;
    let sendP = document.getElementById("sendP").value;
    let requireU = document.getElementById("requireU").value;
    let result = document.getElementById("result").value;

    if (result == "" || result == undefined || result == null) {
      result = this.state.lists;
    }
    console.log(title);
    console.log(sendU);
    console.log(sendP);
    console.log(requireU);
    console.log(result);
    console.log(this.state.lists);
    // axios
    //   .get(
    //     "http://localhost:8099/SendMail/" +
    //       title +
    //       "/" +
    //       sendU +
    //       "/" +
    //       sendP +
    //       "/" +
    //       requireU +
    //       "/" +
    //       result
    //   )
    //   .then(res => {});
    alert("发送成功！！！");
    // window.location.reload(true);
  }
  render() {
    return (
      <div>
        <Card title={"天气 查询"}>
          <span>天气查询</span>
          <hr></hr>
          <Input
            id="city"
            style={{
              margin: "20px",
              width: "300px"
            }}
          />
          <Button type="primary" onClick={this.GetDate}>
            天气查询
          </Button>
          <span> </span>
          <Button type="primary" onClick={this.showModal}>
            邮件发送
          </Button>
          <Modal
            title="邮件 发送"
            visible={this.state.visible}
            onOk={this.hideModal}
            onCancel={this.hideModal}
            okText="ok"
            cancelText="sure"
          >
            <span>发送邮件标题:</span>
            <Input id="title" placeholder="发送邮件标题" className="style" />
            <br></br>
            <span>发送人邮件地址:</span>
            <Input id="sendU" placeholder="发送人邮件地址" className="style" />
            <br></br>
            <span>发送人邮箱密码:</span>
            <Input.Password
              id="sendP"
              placeholder="发送人邮箱密码"
              className="style"
            />
            <br></br>
            <span>收件人邮箱地址:</span>
            <Input
              id="requireU"
              placeholder="收件人邮箱地址"
              className="style"
            />
            <br></br>
            <span>发送邮件内容:</span>
            <Input id="result" placeholder="发送邮件内容" className="style" />
            <br></br>
            <br></br>
            <Button
              icon="login"
              onClick={this.SendMail}
              className="style-button"
              type="primary"
            >
              SendMail
            </Button>
          </Modal>
          <Input.TextArea
            value={this.state.lists}
            style={{
              height: "100px"
            }}
          />
        </Card>
      </div>
    );
  }
}
export default Wfl;
