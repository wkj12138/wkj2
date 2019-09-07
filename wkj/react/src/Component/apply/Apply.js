import React, { Component } from "react";
import { Layout, Menu, Icon, Input, Button } from "antd";
import Wea from "./Wea";
import News from "./News";
import Tran from "./Tran";
import Sele from "./Sele";
import { HashRouter, Switch, Route, Link } from "react-router-dom";
import "../css/main.less";
const { Header, Sider, Content, Footer } = Layout;
const MenuItem = Menu.Item;

class Apply extends Component {
  constructor(props) {
    super(props);
    this.state = {
      collapsed: false
    };
    this.toggle = this.toggle.bind(this);
  }
  toggle() {
    this.setState({
      collapsed: !this.state.collapsed
    });
  }
  render() {
    return (
      <HashRouter>
        <Layout className="c1-layout">
          <Sider trigger={null} collapsible collapsed={this.state.collapsed}>
            <div className="logo">应用</div>
            <Menu theme="dark" mode="inline" defaultSelectedKeys={["1"]}>
              <MenuItem key="1">
                <Icon type="ant-cloud" />
                <span>天气查询</span>
                <Link to="/" />
              </MenuItem>
              <MenuItem key="2">
                <Icon type="book" />
                <span>新闻查询</span>
                <Link to="/1" />
              </MenuItem>
              <MenuItem key="3">
                <Icon type="sync" />
                <span>中英翻译</span>
                <Link to="/2" />
              </MenuItem>
              <MenuItem key="4">
                <Icon type="search" />
                <span>人员查询</span>
                <Link to="/3" />
              </MenuItem>
            </Menu>
          </Sider>
          <Layout>
            <Header style={{ background: "#fff", padding: 0 }}>
              <Icon
                className="trigger"
                type={this.state.collapsed ? "menu-unfold" : "menu-fold"}
                onClick={this.toggle}
              />
            </Header>
            <Content
              style={{
                margin: "24px 16px",
                padding: 24,
                background: "#fff",
                minHeight: 280
              }}
            >
              <Switch>
                <Route path="/" exact component={Wea} />
                <Route path="/1" component={News} />
                <Route path="/2" component={Tran} />
                <Route path="/3" component={Sele} />
              </Switch>
            </Content>
          </Layout>
        </Layout>
      </HashRouter>
    );
  }
}
export default Apply;
