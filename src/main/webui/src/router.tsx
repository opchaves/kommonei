import React from 'react';
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import { HomeOutlined, SettingOutlined } from '@ant-design/icons';
import { Spin } from 'antd';

type Route = {
  label: string;
  icon: React.ReactNode;
  path: string;
  element: Promise<React.FC>;
};

// eslint-disable-next-line react-refresh/only-export-components
export const routes: Route[] = [
  {
    label: 'Home',
    path: '/',
    icon: <HomeOutlined />,
    element: import('./pages/home').then(({ HomePage }) => HomePage),
  },
  {
    label: 'Settings',
    path: '/settings',
    icon: <SettingOutlined />,
    element: import('./pages/settings').then(({ SettingsPage }) => SettingsPage),
  },
];

const router = createBrowserRouter([
  {
    path: '/',
    lazy: () => import('./layout').then(({ Layout }) => ({ Component: Layout })),
    children: routes.map((route) => ({
      path: route.path,
      lazy: () => route.element.then((Component) => ({ Component })),
    })),
  },
]);

export const Router = () => {
  return <RouterProvider router={router} fallbackElement={<Spin spinning />} />;
};
