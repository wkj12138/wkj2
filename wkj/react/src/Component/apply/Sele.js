import React, { Component } from "react";
import axios from "axios";
import { Button, Input, Table, Card } from "antd";
class Sele extends Component {
  constructor(props) {
    super(props);
    this.state = {
      lists: []
    };
    this.GetData = this.GetData.bind(this);
  }
  GetData() {
    var usercode = document.getElementById("usercode").value;

    axios.get("http://localhost:8099/search/" + usercode).then(res => {
      console.log(res);
      console.log(res.data);
      this.setState({
        lists: [res.data]
      });
      console.log(this.state.lists);
    });
    console.log(this.state.lists);
  }
  render() {
    const columns = [
      {
        title: "员工编号",
        dataIndex: "usercode",
        key: "usercode"
      },
      {
        title: "员工名字",
        dataIndex: "username",
        key: "username"
      },
      {
        title: "员工部门",
        dataIndex: "department",
        key: "department"
      },
      {
        title: "入职时间",
        dataIndex: "hiredate",
        key: "hiredate"
      }
    ];

    return (
      <div>
        <Card title={"人员查询"}>
          <Input
            placeholder="Please input the usercode"
            id="usercode"
            style={{
              margin: "10px",
              width: "300px"
            }}
          />

          <Button type="primary" onClick={this.GetData}>
            人员查询
          </Button>
          {/* <Input.TextArea
            value={this.state.lists}
            style={{
              height: "250px"
            }}
          /> */}

          <Table columns={columns} dataSource={this.state.lists} bordered />
        </Card>
      </div>
    );
  }
}
export default Sele;
