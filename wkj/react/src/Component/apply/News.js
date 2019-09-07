import React, { Component } from "react";
import axios from "axios";
import { Card, Table, Button, Modal, Input, Select } from "antd";
import "../css/news.css";
class News extends Component {
  constructor(props) {
    super(props);
    this.state = {
      id: "",
      index: 1,
      lists: "",
      visible: false
    };
    this.GetData = this.GetData.bind(this);
    this.onChange = this.onChange.bind(this);
    this.onChange1 = this.onChange1.bind(this);
    this.SendMail = this.SendMail.bind(this);
    this.showModal = this.showModal.bind(this);
    this.hideModal = this.hideModal.bind(this);
  }
  onChange(value) {
    // console.log(`${value}`);
    this.setState({
      id: `${value}`
    });
  }
  onChange1(value) {
    // console.log(`${value}`);
    this.setState({
      index: `${value}`
    });
  }
  GetData() {
    var index = this.state.id;
    if (index == "" || index == undefined || index == null) {
      alert("无内容可查询，请重新选择类别查询");
    } else {
      var type = this.state.index;
      //   console.log(index);
      //   console.log(type);
      axios
        .get("http://localhost:8099/News/" + index + "/" + type)
        .then(res => {
          console.log(res);
          console.log(res.data);
          this.setState({
            lists: res.data
          });
        });
    }
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
    let nid = this.state.id;
    let gid = this.state.index;
    let sid = this.state.index;

    if (result == "" || result == undefined || result == null) {
      result = this.state.lists;
    }
    console.log(title);
    console.log(sendU);
    console.log(sendP);
    console.log(requireU);
    console.log(nid);
    console.log(gid);
    console.log(sid);
    console.log(this.state.lists);
    // axios
    //   .get(
    //     "http://localhost:8099/SendNMail/" +
    //       title +
    //       "/" +
    //       sendU +
    //       "/" +
    //       sendP +
    //       "/" +
    //       requireU +
    //       "/" +
    //       nid +
    //       "/" +
    //       gid +
    //       "/" +
    //       sid
    //   )
    //   .then(res => {
    //     console.log(res);
    //   });
    alert("发送成功！！！");
    // window.location.reload(true);
  }
  render() {
    return (
      <div>
        <Card title={"新闻查询"}>
          <Select
            showSearch
            style={{
              margin: "10px",
              width: "300px"
            }}
            placeholder="Search the News"
            onChange={this.onChange}
          >
            <Option value="0">头条</Option>
            <Option value="1">社会</Option>
            <Option value="2">国内</Option>
            <Option value="3">国际</Option>
            <Option value="4">娱乐</Option>
            <Option value="5">体育</Option>
            <Option value="6">军事</Option>
            <Option value="7">科技</Option>
            <Option value="8">财经</Option>
            <Option value="9">时尚</Option>
          </Select>

          <Button type="primary" onClick={this.GetData}>
            新闻查询
          </Button>
          <span> </span>
          {/* <Button type="primary" onClick={this.showModal}>
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
            <Input
              id="result"
              placeholder="发送邮件内容(若内容为空，则发送查询数据)"
              className="style"
            />
            <br></br>
            <span>发送邮件形式:</span>
            <Select
              showSearch
              className="style"
              placeholder="邮件形式"
              onChange={this.onChange1}
            >
              <Option value="1">以普通文本发送</Option>
              <Option value="2">以表格文本发送</Option>
            </Select>
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
          </Modal> */}
          <Input.TextArea
            value={this.state.lists}
            style={{
              height: "250px"
            }}
          />
        </Card>
      </div>
    );
  }
}
export default News;
