import React, { useCallback, useMemo, useState } from 'react';
import { Outlet, useLocation, useNavigate, useNavigation } from 'react-router-dom';
import { MenuFoldOutlined, MenuUnfoldOutlined } from '@ant-design/icons';
import { Button, Layout as AntdLayout, Menu, theme, MenuProps } from 'antd';
import { routes } from '../router';

const { Header, Sider, Content } = AntdLayout;

export const Layout: React.FC = () => {
  const [collapsed, setCollapsed] = useState(false);
  const {
    token: { colorBgContainer, borderRadiusLG },
  } = theme.useToken();

  const navigation = useNavigation();
  const { pathname } = useLocation();
  const navigate = useNavigate();

  const handleClick = useCallback<NonNullable<MenuProps['onClick']>>(
    ({ key }) => {
      if (pathname !== key) {
        navigate(key);
      }
    },
    [navigate, pathname],
  );

  const menus = useMemo(
    () => routes.map(({ path, label, icon }) => ({ key: path, label, icon })),
    [],
  );

  return (
    <AntdLayout style={{ minHeight: '100vh' }}>
      <Sider trigger={null} collapsible collapsed={collapsed}>
        <div className="demo-logo-vertical" />
        <Menu
          theme="dark"
          mode="inline"
          defaultSelectedKeys={[pathname]}
          onClick={handleClick}
          items={menus}
        />
      </Sider>
      <AntdLayout>
        <Header style={{ padding: 0, background: colorBgContainer }}>
          <Button
            type="text"
            icon={collapsed ? <MenuUnfoldOutlined /> : <MenuFoldOutlined />}
            onClick={() => setCollapsed(!collapsed)}
            style={{
              fontSize: '16px',
              width: 64,
              height: 64,
            }}
          />
        </Header>
        <Content
          style={{
            margin: '24px 16px',
            width: 'calc(100vw - 230px)',
            background: colorBgContainer,
            borderRadius: borderRadiusLG,
          }}
        >
          {navigation.state === 'loading' && 'loading...'}

          <Outlet />
        </Content>
      </AntdLayout>
    </AntdLayout>
  );
};
