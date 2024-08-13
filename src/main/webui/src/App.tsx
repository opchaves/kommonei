import { ConfigProvider } from 'antd';
import { Router } from './router';

export const App = () => {
  return (
    <ConfigProvider>
      <Router />
    </ConfigProvider>
  );
};
