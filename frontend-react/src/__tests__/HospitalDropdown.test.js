import React from "react";
import Enzyme, { shallow, mount } from "enzyme";
import HospitalDropdown from "../components/HospitalDropdown";
import Adapter from "enzyme-adapter-react-16";
import * as reactRouterDom from "react-router-dom";
import renderer from "react-test-renderer";
import { Provider } from "react-redux";
import configureStore from "redux-mock-store";

Enzyme.configure({ adapter: new Adapter() });

/**
 * Testing for UI of Login page
 * @author MN097789
 */

const mockNavigate = jest.fn();

jest.mock("react-router-dom", () => ({
  useSelector: jest.fn(),

  useNavigate: () => mockNavigate,
}));
const mockNavigateSpy = jest.spyOn(reactRouterDom, "useNavigate");

describe("HospitalDropdown UI Test Suite", () => {
  const initialState = { output: 10 };
  const mockStore = configureStore();
  let store;

  test("Checks if it renders correctly", () => {
    store = mockStore(initialState);
    const tree = renderer
      .create(
        <Provider store={store}>
          <HospitalDropdown />
        </Provider>
      )
      .toJSON();
    expect(tree).toMatchSnapshot();
  });
});
