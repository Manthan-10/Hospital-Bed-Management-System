import React from "react";
import Enzyme, { shallow, mount } from "enzyme";
import Login from "../components/Login";
import Adapter from "enzyme-adapter-react-16";
import * as reactRouterDom from "react-router-dom";
import renderer from "react-test-renderer";
import AuthService from "../services/auth.service";
import { render } from "@testing-library/react";
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

describe("Login UI Test Suite", () => {
  let wrapper;
  beforeEach(() => {
    wrapper = shallow(<Login />);
  });

  test("Checks if it renders correctly", () => {
    const tree = renderer.create(<Login />).toJSON();
    expect(tree).toMatchSnapshot();
  });

  //Testing if the UI is rendered correctly
  it("should check for divs", () => {
    const wrapper = shallow(<Login />);
    expect(wrapper.find("div.col-md-12").exists()).toBe(true);
    expect(wrapper.find("label").length).toEqual(2);
    expect(wrapper.find("#username").length).toEqual(1);
    expect(wrapper.find("#password").length).toEqual(1);
    expect(wrapper.find("button").length).toEqual(1);
  });

  //Testing for input change during user id
  test("Should set the username on change event", () => {
    wrapper.find("#username").simulate("change", {
      target: { value: "MN097789" },
    });
    expect(wrapper.find("#username").prop("value")).toEqual("MN097789");
  });

  //Testing for input change during password
  test("Should set the password on change event", () => {
    wrapper.find("#password").simulate("change", {
      target: { value: "12345" },
    });
    expect(wrapper.find("#password").prop("value")).toEqual("12345");
  });

  //Testing for successful login
  test("should login successfully", () => {
    const wrapper = mount(<Login />);
    wrapper.find({ username: "MN097789", password: "Manthan123" });
    const formEventMocked = { preventDefault: jest.fn() };
    wrapper.find("form").simulate("submit", formEventMocked);
    expect(formEventMocked.preventDefault).toBeCalledTimes(1);
  });
});
